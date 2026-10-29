package com.pragmabank.domain.port;

import com.pragmabank.domain.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define el contrato para la persistencia de transacciones.
 * Esta interfaz pertenece a la capa de dominio y es implementada por la capa de infraestructura.
 */
public interface TransactionRepository {
    
    /**
     * Persiste una nueva transacción en el repositorio.
     * @param transaction la transacción a guardar
     * @return la transacción persistida con su ID asignado
     */
    Transaction save(Transaction transaction);
    
    /**
     * Busca una transacción por su identificador único.
     * @param id el identificador de la transacción
     * @return un Optional que contiene la transacción si existe
     */
    Optional<Transaction> findById(UUID id);
    
    /**
     * Busca una transacción por su número único de transacción.
     * @param transactionNumber el número de transacción
     * @return un Optional que contiene la transacción si existe
     */
    Optional<Transaction> findByTransactionNumber(String transactionNumber);
    
    /**
     * Recupera todas las transacciones asociadas a una cuenta de origen.
     * @param accountId el identificador de la cuenta de origen
     * @return lista de transacciones realizadas desde esa cuenta
     */
    List<Transaction> findBySourceAccountId(UUID accountId);
    
    /**
     * Recupera todas las transacciones asociadas a una cuenta de destino.
     * @param accountId el identificador de la cuenta de destino
     * @return lista de transacciones recibidas en esa cuenta
     */
    List<Transaction> findByTargetAccountId(UUID accountId);
    
    /**
     * Recupera el historial de transacciones de una cuenta (origen o destino).
     * @param accountId el identificador de la cuenta
     * @return lista combinada de transacciones de origen y destino
     */
    List<Transaction> findByAccountId(UUID accountId);
    
    /**
     * Busca transacciones dentro de un rango de fechas.
     * @param startDate fecha de inicio del rango
     * @param endDate fecha de fin del rango
     * @return lista de transacciones en el rango especificado
     */
    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Calcula el total de transacciones salientes de una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return suma total de los montos de transacciones salientes
     */
    BigDecimal sumAmountsBySourceAccountIdInDateRange(UUID accountId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Calcula el total de transacciones entrantes a una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return suma total de los montos de transacciones entrantes
     */
    BigDecimal sumAmountsByTargetAccountIdInDateRange(UUID accountId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Elimina una transacción por su identificador.
     * @param id el identificador de la transacción a eliminar
     */
    void deleteById(UUID id);
    
    /**
     * Verifica si existe una transacción con el número dado.
     * @param transactionNumber el número de transacción a verificar
     * @return true si existe una transacción con ese número
     */
    boolean existsByTransactionNumber(String transactionNumber);
}