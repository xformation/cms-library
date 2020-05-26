package com.synectiks.library.repository.search;

import com.synectiks.library.domain.User;
import com.synectiks.library.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends JPASearchRepository<User, Long> {
}
