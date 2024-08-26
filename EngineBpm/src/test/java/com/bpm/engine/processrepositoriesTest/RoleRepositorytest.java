package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.Role;
import com.bpm.engine.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositorytest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public  void saveRoleAndReturnRole(){
        //Arrange
        Role role = Role.builder()
                .name("Developer")
                .codeRole("w23a")
                .description("developer").build();
        //Act
        Role saveRole = roleRepository.save(role);

        //Assert
        Assertions.assertThat(saveRole).isNotNull();
        Assertions.assertThat(saveRole.getIdRole()).isGreaterThan(0);
    }

    @Test
    public void roleRepository_GetAll_ReturnMoreThenOneRole() {
        Role role = Role.builder()
                .name("Developer")
                .codeRole("w23a")
                .description("developer").build();
        Role role2 = Role.builder()
                .name("DeveloperII")
                .codeRole("w25a")
                .description("Developer Senor").build();

       roleRepository.save(role);
       roleRepository.save(role2);

        Iterable<Role> allItems = roleRepository.findAll();
        List<Role> roleList = new ArrayList<>((Collection) allItems);


        Assertions.assertThat(roleList).isNotNull();
        Assertions.assertThat(roleList.size()).isEqualTo(2);
    }


    @Test
    public void roleRepository_FindById_ReturnRole() {
        Role role2 = Role.builder()
                .name("DeveloperII")
                .codeRole("w25a")
                .description("Developer Senor")
                .build();

        Role rolex =  roleRepository.save(role2);
        Role role = roleRepository.findById(rolex.getIdRole()).get();
        Assertions.assertThat(role).isNotNull();
    }

    @Test
    public void updateRoleAndReturnRoleUpdate(){
        String name = "PACHECO";
        Role role2 = Role.builder()
                .name("DeveloperII")
                .codeRole("w25a")
                .description("Developer Senor")
                .build();
        Role rolex = roleRepository.save(role2);
        rolex.setName(name);
        roleRepository.save(rolex);
        Role testReturn = roleRepository.findByName(name).get();

        Assertions.assertThat(testReturn).isNotNull();
        Assertions.assertThat(testReturn.getIdRole()).isEqualTo(rolex.getIdRole());
    }


}
