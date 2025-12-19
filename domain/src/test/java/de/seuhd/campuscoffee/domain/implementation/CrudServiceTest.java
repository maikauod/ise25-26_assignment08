package de.seuhd.campuscoffee.domain.implementation;

import de.seuhd.campuscoffee.domain.configuration.ApprovalConfiguration;
import de.seuhd.campuscoffee.domain.exceptions.NotFoundException;
import de.seuhd.campuscoffee.domain.exceptions.ValidationException;
import de.seuhd.campuscoffee.domain.model.objects.Pos;
import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.model.objects.User;
import de.seuhd.campuscoffee.domain.ports.data.PosDataService;
import de.seuhd.campuscoffee.domain.ports.data.ReviewDataService;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
import de.seuhd.campuscoffee.domain.ports.data.UserDataService;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static de.seuhd.campuscoffee.domain.tests.TestFixtures.getApprovalConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {

    @Mock
    private CrudDataService<Pos, Long> dataService;
    private CrudServiceImpl<Pos, Long> service;

    @BeforeEach
    void beforeEach() {
        service = new CrudServiceImpl<Pos, Long>(Pos.class){
            @Override
            protected CrudDataService<Pos, Long> dataService() {
                return dataService;
            }
        };
    }

    @Test
    void testGetAll(){
        Pos pos = TestFixtures.getPosFixtures().getFirst();
        when(dataService.getAll()).thenReturn(List.of(pos));

        //THEN
        List<Pos> result = service.getAll();

        assertThat(result).isEqualTo(List.of(pos));
    }

    @Test
    void testGetById(){
        Pos pos = TestFixtures.getPosFixtures().getFirst();
        when(dataService.getById(pos.getId())).thenReturn(pos);

        Pos result = service.getById(pos.getId());

        assertThat(result).isEqualTo(pos);
    }
}
