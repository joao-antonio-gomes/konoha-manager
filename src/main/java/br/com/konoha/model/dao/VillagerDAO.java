package br.com.konoha.model.dao;

import br.com.konoha.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
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

    public List<VillagerDTO> listAllVillagers() throws SQLException {
        try (Statement statement = new ConnectionFactoryJDBC().getConnection().createStatement()) {
            String sql = "SELECT * FROM naruto_manager.villager";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            List<VillagerDTO> villagerDTOS = new ArrayList<>();
            while (resultSet.next()) {
                VillagerDTO villagerDTO = new VillagerDTO(
                        resultSet.getString("vil_name"),
                        resultSet.getString("vil_surname"),
                        resultSet.getInt("vil_age"),
                        resultSet.getDouble("vil_cost")
                );
                villagerDTO.setId(resultSet.getInt("vil_id"));
                villagerDTOS.add(villagerDTO);
            }
            return villagerDTOS;
        }
    }

    public VillagerDTO getVillagerById(BigInteger id) throws SQLException {
        try (Statement statement = new ConnectionFactoryJDBC().getConnection().createStatement()) {
            String sql = "SELECT * FROM naruto_manager.villager WHERE vil_id = " + id;
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            VillagerDTO villagerDTO = null;
            while (resultSet.next()) {
                villagerDTO = new VillagerDTO(
                        resultSet.getString("vil_name"),
                        resultSet.getString("vil_surname"),
                        resultSet.getInt("vil_age"),
                        resultSet.getDouble("vil_cost")
                );
                villagerDTO.setId(resultSet.getInt("vil_id"));
            }
            return villagerDTO;
        }
    }
}
