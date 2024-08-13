package com.ux.ux.repositories;

import java.util.List;
import java.util.UUID;

import com.ux.ux.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ux.ux.models.VendaModel;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, UUID>{
    List<VendaModel> findByCliente(ClienteModel cliente);

    @Query(value = """
        WITH vendas_mensais AS (
            SELECT
                c.idcliente AS cliente_id,
                c.nome AS cliente_nome,
                DATE_TRUNC('month', TO_DATE(v.data, 'DD-MM-YYYY')) AS mes,
                COUNT(v.id_venda) AS num_vendas,
                SUM(CAST(v.valor AS NUMERIC(10,2))) AS soma_valor
            FROM tb_vendas v
            JOIN tb_clientes c ON v.id_cliente = c.idcliente
            GROUP BY c.idcliente, c.nome, mes
        ),
        cliente_mais_vendas AS (
            SELECT
                mes,
                cliente_nome,
                num_vendas
            FROM vendas_mensais
            WHERE (mes, num_vendas) IN (
                SELECT
                    mes,
                    MAX(num_vendas)
                FROM vendas_mensais
                GROUP BY mes
            )
        ),
        cliente_mais_valor AS (
            SELECT
                mes,
                cliente_nome,
                soma_valor
            FROM vendas_mensais
            WHERE (mes, soma_valor) IN (
                SELECT
                    mes,
                    MAX(soma_valor)
                FROM vendas_mensais
                GROUP BY mes
            )
        )
        SELECT
            c_m_v.cliente_nome AS cliente_mais_numero_vendas,
            c_m_v.num_vendas AS total_vendas_cliente_mais_numero,
            TO_CHAR(c_m_v.mes, 'DD-MM-YYYY') AS mes_formatado,
            c_m_v2.cliente_nome AS cliente_mais_valor_vendas,
            CAST(c_m_v2.soma_valor AS numeric) AS total_valor_cliente_mais_valor
        FROM cliente_mais_vendas c_m_v
        JOIN cliente_mais_valor c_m_v2 ON c_m_v.mes = c_m_v2.mes
        ORDER BY mes_formatado
    """, nativeQuery = true)
    List<Object[]> findVendasMensais();

    @Query(value = """
        WITH vendas_anuais AS (
            SELECT
                c.idcliente AS cliente_id,
                c.nome AS cliente_nome,
                EXTRACT(YEAR FROM TO_DATE(v.data, 'DD-MM-YYYY')) AS ano,
                COUNT(v.id_venda) AS num_vendas,
                SUM(CAST(v.valor AS NUMERIC(10,2))) AS soma_valor
            FROM tb_vendas v
            JOIN tb_clientes c ON v.id_cliente = c.idcliente
            GROUP BY c.idcliente, c.nome, ano
        ),
        cliente_mais_vendas_ano AS (
            SELECT
                ano,
                cliente_nome,
                num_vendas
            FROM vendas_anuais
            WHERE (ano, num_vendas) IN (
                SELECT
                    ano,
                    MAX(num_vendas)
                FROM vendas_anuais
                GROUP BY ano
            )
        ),
        cliente_mais_valor_ano AS (
            SELECT
                ano,
                cliente_nome,
                soma_valor
            FROM vendas_anuais
            WHERE (ano, soma_valor) IN (
                SELECT
                    ano,
                    MAX(soma_valor)
                FROM vendas_anuais
                GROUP BY ano
            )
        )
        SELECT
            c_m_v_ano.cliente_nome AS cliente_mais_numero_vendas_ano,
            c_m_v_ano.num_vendas AS total_vendas_cliente_mais_numero_ano,
            c_m_v_ano.ano AS ano_formatado,
            c_m_v2_ano.cliente_nome AS cliente_mais_valor_vendas_ano,
            CAST(c_m_v2_ano.soma_valor AS numeric) AS total_valor_cliente_mais_valor_ano
        FROM cliente_mais_vendas_ano c_m_v_ano
        JOIN cliente_mais_valor_ano c_m_v2_ano ON c_m_v_ano.ano = c_m_v2_ano.ano
        ORDER BY ano_formatado
    """, nativeQuery = true)
    List<Object[]> findVendasAnuais(String ano);
}
