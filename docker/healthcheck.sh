#!/bin/bash
# Healthcheck script para el contenedor del servicio bancario
# Verifica la disponibilidad del servicio y sus dependencias

set -e

# Configuración
SERVICE_HOST="${SERVICE_HOST:-localhost}"
SERVICE_PORT="${SERVICE_PORT:-8080}"
ACTUATOR_HEALTH_URL="http://${SERVICE_HOST}:${SERVICE_PORT}/actuator/health"
MAX_RETRIES="${HEALTHCHECK_RETRIES:-30}"
RETRY_INTERVAL="${HEALTHCHECK_INTERVAL:-2}"

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

log_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

log_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Función para verificar la conectividad a PostgreSQL
check_postgres() {
    local pg_host="${POSTGRES_HOST:-localhost}"
    local pg_port="${POSTGRES_PORT:-5432}"
    local pg_user="${POSTGRES_USER:-postgres}"
    local pg_db="${POSTGRES_DB:-banco}"
    
    if command -v pg_isready &> /dev/null; then
        if pg_isready -h "$pg_host" -p "$pg_port" -U "$pg_user" -d "$pg_db" &> /dev/null; then
            log_info "PostgreSQL está disponible en ${pg_host}:${pg_port}"
            return 0
        else
            log_error "PostgreSQL no está disponible en ${pg_host}:${pg_port}"
            return 1
        fi
    else
        # Alternativa usando nc (netcat) si pg_isready no está disponible
        if command -v nc &> /dev/null; then
            if nc -z "$pg_host" "$pg_port" 2>/dev/null; then
                log_info "Puerto PostgreSQL ${pg_host}:${pg_port} accesible"
                return 0
            else
                log_error "Puerto PostgreSQL ${pg_host}:${pg_port} no accesible"
                return 1
            fi
        else
            log_warn "No se puede verificar PostgreSQL (pg_isready y nc no disponibles)"
            return 0
        fi
    fi
}

# Función para verificar el endpoint de health de Spring Boot
check_actuator_health() {
    local attempt=1
    local max_attempts="${MAX_RETRIES}"
    
    while [ $attempt -le $max_attempts ]; do
        log_info "Verificando health endpoint (intento ${attempt}/${max_attempts}): ${ACTUATOR_HEALTH_URL}"
        
        # Verificar si curl o wget está disponible
        if command -v curl &> /dev/null; then
            HTTP_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" "${ACTUATOR_HEALTH_URL}" 2>/dev/null || echo "000")
        elif command -v wget &> /dev/null; then
            HTTP_RESPONSE=$(wget -q -O /dev/null "${ACTUATOR_HEALTH_URL}" 2>/dev/null && echo "200" || echo "000")
        else
            log_error "Ni curl ni wget están disponibles para verificar el health endpoint"
            return 1
        fi
        
        case "$HTTP_RESPONSE" in
            "200")
                log_info "Health check exitoso: HTTP ${HTTP_RESPONSE}"
                
                # Verificar el estado detallado si es posible
                if command -v curl &> /dev/null; then
                    HEALTH_DETAILS=$(curl -s "${ACTUATOR_HEALTH_URL}" 2>/dev/null || echo "{}")
                    DB_STATUS=$(echo "$HEALTH_DETAILS" | grep -o '"db":{[^}]*}' || echo "")
                    if [ -n "$DB_STATUS" ]; then
                        if echo "$DB_STATUS" | grep -q '"status":"UP"'; then
                            log_info "Componente DB reportado como UP"
                        else
                            log_warn "Componente DB puede tener problemas"
                        fi
                    fi
                fi
                return 0
                ;;
            "000")
                log_warn "Servicio no responde (intento ${attempt}/${max_attempts})"
                ;;
            *)
                log_error "Respuesta inesperada del servicio: HTTP ${HTTP_RESPONSE}"
                ;;
        esac
        
        # Si no es el último intento, esperar antes de reintentar
        if [ $attempt -lt $max_attempts ]; then
            log_info "Esperando ${RETRY_INTERVAL} segundos antes del siguiente intento..."
            sleep "${RETRY_INTERVAL}"
        fi
        
        attempt=$((attempt + 1))
    done
    
    log_error "Health check falló después de ${max_attempts} intentos"
    return 1
}

# Función principal de verificación
main() {
    log_info "=== Iniciando verificación de salud del contenedor ==="
    log_info "Host del servicio: ${SERVICE_HOST}:${SERVICE_PORT}"
    
    # Verificar que el servicio esté escuchando en el puerto
    if command -v nc &> /dev/null; then
        if ! nc -z "${SERVICE_HOST}" "${SERVICE_PORT}" 2>/dev/null; then
            log_error "El servicio no está escuchando en ${SERVICE_HOST}:${SERVICE_PORT}"
            exit 1
        fi
        log_info "Puerto del servicio ${SERVICE_HOST}:${SERVICE_PORT} accesible"
    fi
    
    # Verificar PostgreSQL
    if ! check_postgres; then
        log_error "Verificación de PostgreSQL falló"
        exit 1
    fi
    
    # Verificar el health endpoint de Spring Boot Actuator
    if ! check_actuator_health; then
        log_error "Verificación del health endpoint falló"
        exit 1
    fi
    
    log_info "=== Todas las verificaciones de salud pasaron exitosamente ==="
    exit 0
}

# Ejecutar la función principal
main "$@"