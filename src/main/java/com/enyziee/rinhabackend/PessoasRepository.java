package com.enyziee.rinhabackend;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends CrudRepository<Pessoas, UUID> {

    @Query("SELECT * FROM pessoas WHERE " +
            "nome ILIKE concat('%', :termo, '%') OR " +
            "apelido ILIKE concat('%', :termo, '%') OR " +
            "array_to_string(stack, ' ') ILIKE concat('%', :termo, '%')")
    List<Pessoas> searchByTerm(@Param("termo") String termo);

    boolean existsByApelido(@Param("apelido") String apelido);

}
