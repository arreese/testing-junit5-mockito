package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
//  Creates instance of the service and inject the Mock class (SpecialRepository)
//  into the constructor for us
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);

//        Want to make sure the delete was called with any Object
//        that has the Specialty class
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest(){
//        Object that the mock is going to return back
        Speciality speciality = new Speciality();

//        When findById(1) gets a call, return the optional of
//        that specialty object we created
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

//        Make the method call under the class that is being tested
        Speciality foundSpecialty = service.findById(anyLong());

//        Make sure that we get a specialty object back
        assertThat(foundSpecialty).isNotNull();

//        Verify that our findById was called 1 time
        verify(specialtyRepository).findById(1L);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLest() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        service.delete((new Speciality()));
    }
}