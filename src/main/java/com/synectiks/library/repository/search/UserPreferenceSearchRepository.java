package com.synectiks.library.repository.search;

import com.synectiks.library.domain.UserPreference;
import com.synectiks.library.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserPreference} entity.
 */
public interface UserPreferenceSearchRepository extends JPASearchRepository<UserPreference, Long> {
}
