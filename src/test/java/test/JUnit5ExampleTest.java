package test;

import lombok.RequiredArgsConstructor;
import model.Person;
import org.junit.jupiter.api.Test;
import repositories.PersonRepository;
import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
class JUnit5ExampleTest {

    private final PersonRepository personRepository;

    @Test
    void test1() {

        Person p1 = new Person("Peter", "123456");
        personRepository.save(p1);

        Person p2 = personRepository.findAll().get(0);
        assertThat(p2.getName()).isEqualTo("Peter");
    }
}
