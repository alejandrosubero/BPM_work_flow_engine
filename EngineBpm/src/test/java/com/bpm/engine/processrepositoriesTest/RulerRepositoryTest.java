package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.repository.RulerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RulerRepositoryTest {

    @Autowired
    private RulerRepository rulerRepository;

    @Test
    public  void saveRulerAndReturnRuler(){
        Integer action = 1;
        String condition = "APRUBE";

        //Arrange
        Ruler testObject = Ruler.builder()
                .condition(condition)
                .action(action).build();

        //Act
        Ruler saveTestObject = rulerRepository.save(testObject);

        //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getIdRuler()).isGreaterThan(0);
    }


    @Test
    public  void findRulerByConditionAndActionAndReturnRuler(){
        //Arrange
        Integer action = 1;
        String condition = "APRUBE";

        //Arrange
        Ruler testObject = Ruler.builder()
                .condition(condition)
                .action(action).build();
        Ruler testObject2 = Ruler.builder()
                .condition(condition)
                .action(action).build();
        //Act
        rulerRepository.save(testObject);
        rulerRepository.save(testObject2);

        List<Ruler> saveTestObjectCondition = rulerRepository.findByCondition(condition);
        List<Ruler> saveTestObjectAction = rulerRepository.findByAction(action);

        //Assert
        Assertions.assertThat(saveTestObjectCondition).isNotNull();
        Assertions.assertThat(saveTestObjectCondition.size()).isEqualTo(2);

        Assertions.assertThat(saveTestObjectAction).isNotNull();
        Assertions.assertThat(saveTestObjectAction.size()).isEqualTo(2);

    }
}
