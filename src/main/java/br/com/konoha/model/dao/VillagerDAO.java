package br.com.konoha.model.dao;

import br.com.konoha.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class VillagerDAO {

    public VillagerDTO create(VillagerDTO villagerDTO) throws SQLException {
        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            String sql =
                    "INSERT INTO naruto_manager.villager (vil_name, vil_surname, vil_age, vil_cost) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, villagerDTO.getName());
            preparedStatement.setString(2, villagerDTO.getSurname());
            preparedStatement.setInt(3, villagerDTO.getAge());
            preparedStatement.setDouble(4, villagerDTO.getCost());

            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                villagerDTO.setId(generatedKeys.getInt(1));
            }
        }
        return villagerDTO;
    }

    public static List<VillagerDTO> villagers = Arrays.asList(
            new VillagerDTO("João", "Gomes", 24, 50.0),
            new VillagerDTO("Andriele", "Portela", 22, 25.0),
            new VillagerDTO("Geruza", "Prim", 19, 30.0)
    );


    public List<VillagerDTO> listAllVillagers() {
        return villagers;
    }
}
