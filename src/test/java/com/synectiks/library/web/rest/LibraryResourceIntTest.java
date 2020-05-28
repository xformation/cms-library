package com.synectiks.library.web.rest;

import com.synectiks.library.LibraryApp;

import com.synectiks.library.domain.Library;
import com.synectiks.library.repository.LibraryRepository;
import com.synectiks.library.service.LibraryService;
import com.synectiks.library.service.dto.LibraryDTO;
import com.synectiks.library.service.mapper.LibraryMapper;
import com.synectiks.library.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.synectiks.library.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LibraryResource REST controller.
 *
 * @see LibraryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApp.class)
public class LibraryResourceIntTest {

    private static final String DEFAULT_CL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BOOK_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_BOOK_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final Long DEFAULT_NO_OF_COPIES = 1L;
    private static final Long UPDATED_NO_OF_COPIES = 2L;

    private static final Long DEFAULT_BOOK_NO = 1L;
    private static final Long UPDATED_BOOK_NO = 2L;

    private static final String DEFAULT_ADDITIONAL_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_INFO = "BBBBBBBBBB";

    private static final Long DEFAULT_UNIQUE_NO = 1L;
    private static final Long UPDATED_UNIQUE_NO = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryMapper libraryMapper;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restLibraryMockMvc;

    private Library library;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LibraryResource libraryResource = new LibraryResource(libraryService);
        this.restLibraryMockMvc = MockMvcBuilders.standaloneSetup(libraryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Library createEntity(EntityManager em) {
        Library library = new Library()
            .clNo(DEFAULT_CL_NO)
            .bookTitle(DEFAULT_BOOK_TITLE)
            .author(DEFAULT_AUTHOR)
            .noOfCopies(DEFAULT_NO_OF_COPIES)
            .bookNo(DEFAULT_BOOK_NO)
            .additionalInfo(DEFAULT_ADDITIONAL_INFO)
            .uniqueNo(DEFAULT_UNIQUE_NO)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .branchId(DEFAULT_BRANCH_ID);
        return library;
    }

    @Before
    public void initTest() {
        library = createEntity(em);
    }

    @Test
    @Transactional
    public void createLibrary() throws Exception {
        int databaseSizeBeforeCreate = libraryRepository.findAll().size();

        // Create the Library
        LibraryDTO libraryDTO = libraryMapper.toDto(library);
        restLibraryMockMvc.perform(post("/api/libraries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryDTO)))
            .andExpect(status().isCreated());

        // Validate the Library in the database
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList).hasSize(databaseSizeBeforeCreate + 1);
        Library testLibrary = libraryList.get(libraryList.size() - 1);
        assertThat(testLibrary.getClNo()).isEqualTo(DEFAULT_CL_NO);
        assertThat(testLibrary.getBookTitle()).isEqualTo(DEFAULT_BOOK_TITLE);
        assertThat(testLibrary.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testLibrary.getNoOfCopies()).isEqualTo(DEFAULT_NO_OF_COPIES);
        assertThat(testLibrary.getBookNo()).isEqualTo(DEFAULT_BOOK_NO);
        assertThat(testLibrary.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testLibrary.getUniqueNo()).isEqualTo(DEFAULT_UNIQUE_NO);
        assertThat(testLibrary.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLibrary.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
    }

    @Test
    @Transactional
    public void createLibraryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = libraryRepository.findAll().size();

        // Create the Library with an existing ID
        library.setId(1L);
        LibraryDTO libraryDTO = libraryMapper.toDto(library);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibraryMockMvc.perform(post("/api/libraries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Library in the database
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLibraries() throws Exception {
        // Initialize the database
        libraryRepository.saveAndFlush(library);

        // Get all the libraryList
        restLibraryMockMvc.perform(get("/api/libraries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(library.getId().intValue())))
            .andExpect(jsonPath("$.[*].clNo").value(hasItem(DEFAULT_CL_NO.toString())))
            .andExpect(jsonPath("$.[*].bookTitle").value(hasItem(DEFAULT_BOOK_TITLE.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())))
            .andExpect(jsonPath("$.[*].noOfCopies").value(hasItem(DEFAULT_NO_OF_COPIES.intValue())))
            .andExpect(jsonPath("$.[*].bookNo").value(hasItem(DEFAULT_BOOK_NO.intValue())))
            .andExpect(jsonPath("$.[*].additionalInfo").value(hasItem(DEFAULT_ADDITIONAL_INFO.toString())))
            .andExpect(jsonPath("$.[*].uniqueNo").value(hasItem(DEFAULT_UNIQUE_NO.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())));
    }

    @Test
    @Transactional
    public void getLibrary() throws Exception {
        // Initialize the database
        libraryRepository.saveAndFlush(library);

        // Get the library
        restLibraryMockMvc.perform(get("/api/libraries/{id}", library.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(library.getId().intValue()))
            .andExpect(jsonPath("$.clNo").value(DEFAULT_CL_NO.toString()))
            .andExpect(jsonPath("$.bookTitle").value(DEFAULT_BOOK_TITLE.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()))
            .andExpect(jsonPath("$.noOfCopies").value(DEFAULT_NO_OF_COPIES.intValue()))
            .andExpect(jsonPath("$.bookNo").value(DEFAULT_BOOK_NO.intValue()))
            .andExpect(jsonPath("$.additionalInfo").value(DEFAULT_ADDITIONAL_INFO.toString()))
            .andExpect(jsonPath("$.uniqueNo").value(DEFAULT_UNIQUE_NO.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLibrary() throws Exception {
        // Get the library
        restLibraryMockMvc.perform(get("/api/libraries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLibrary() throws Exception {
        // Initialize the database
        libraryRepository.saveAndFlush(library);

        int databaseSizeBeforeUpdate = libraryRepository.findAll().size();

        // Update the library
        Library updatedLibrary = libraryRepository.findById(library.getId()).get();
        // Disconnect from session so that the updates on updatedLibrary are not directly saved in db
        em.detach(updatedLibrary);
        updatedLibrary
            .clNo(UPDATED_CL_NO)
            .bookTitle(UPDATED_BOOK_TITLE)
            .author(UPDATED_AUTHOR)
            .noOfCopies(UPDATED_NO_OF_COPIES)
            .bookNo(UPDATED_BOOK_NO)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .uniqueNo(UPDATED_UNIQUE_NO)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .branchId(UPDATED_BRANCH_ID);
        LibraryDTO libraryDTO = libraryMapper.toDto(updatedLibrary);

        restLibraryMockMvc.perform(put("/api/libraries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryDTO)))
            .andExpect(status().isOk());

        // Validate the Library in the database
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList).hasSize(databaseSizeBeforeUpdate);
        Library testLibrary = libraryList.get(libraryList.size() - 1);
        assertThat(testLibrary.getClNo()).isEqualTo(UPDATED_CL_NO);
        assertThat(testLibrary.getBookTitle()).isEqualTo(UPDATED_BOOK_TITLE);
        assertThat(testLibrary.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testLibrary.getNoOfCopies()).isEqualTo(UPDATED_NO_OF_COPIES);
        assertThat(testLibrary.getBookNo()).isEqualTo(UPDATED_BOOK_NO);
        assertThat(testLibrary.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testLibrary.getUniqueNo()).isEqualTo(UPDATED_UNIQUE_NO);
        assertThat(testLibrary.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLibrary.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingLibrary() throws Exception {
        int databaseSizeBeforeUpdate = libraryRepository.findAll().size();

        // Create the Library
        LibraryDTO libraryDTO = libraryMapper.toDto(library);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibraryMockMvc.perform(put("/api/libraries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Library in the database
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLibrary() throws Exception {
        // Initialize the database
        libraryRepository.saveAndFlush(library);

        int databaseSizeBeforeDelete = libraryRepository.findAll().size();

        // Delete the library
        restLibraryMockMvc.perform(delete("/api/libraries/{id}", library.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Library.class);
        Library library1 = new Library();
        library1.setId(1L);
        Library library2 = new Library();
        library2.setId(library1.getId());
        assertThat(library1).isEqualTo(library2);
        library2.setId(2L);
        assertThat(library1).isNotEqualTo(library2);
        library1.setId(null);
        assertThat(library1).isNotEqualTo(library2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibraryDTO.class);
        LibraryDTO libraryDTO1 = new LibraryDTO();
        libraryDTO1.setId(1L);
        LibraryDTO libraryDTO2 = new LibraryDTO();
        assertThat(libraryDTO1).isNotEqualTo(libraryDTO2);
        libraryDTO2.setId(libraryDTO1.getId());
        assertThat(libraryDTO1).isEqualTo(libraryDTO2);
        libraryDTO2.setId(2L);
        assertThat(libraryDTO1).isNotEqualTo(libraryDTO2);
        libraryDTO1.setId(null);
        assertThat(libraryDTO1).isNotEqualTo(libraryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(libraryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(libraryMapper.fromId(null)).isNull();
    }
}
