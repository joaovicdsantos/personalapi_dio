package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSaveMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavePerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavePerson);
        MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(expectedSavePerson);
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSucessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Person expectedSavePerson) {
        return MessageResponseDTO.builder().message(String.format("Created person with ID{%s}", expectedSavePerson.getId())).build();
    }
}

