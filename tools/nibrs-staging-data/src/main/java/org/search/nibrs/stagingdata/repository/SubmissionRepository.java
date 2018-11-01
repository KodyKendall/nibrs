/*
 * Copyright 2016 SEARCH-The National Consortium for Justice Information and Statistics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.search.nibrs.stagingdata.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.search.nibrs.stagingdata.model.Submission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface SubmissionRepository 
	extends JpaRepository<Submission, Integer>{
	
	@EntityGraph(value="allSubmissionJoins", type=EntityGraphType.LOAD)
	Submission findByIncidentIdentifier(String incidentIdentifier);
	
	@EntityGraph(value="allSubmissionJoins", type=EntityGraphType.LOAD)
	Submission findBySubmissionId(Integer submissionId);
	
	@EntityGraph(value="allSubmissionJoins", type=EntityGraphType.LOAD)
	List<Submission> findAll(Iterable<Integer> ids);
	
	@Query("SELECT DISTINCT s.submissionId from Submission s "
			+ "WHERE s.incidentIdentifier = ?1 ")
	List<Integer> findIdsByIncidentIdentifier(String incidentIdentifier);
	
}
