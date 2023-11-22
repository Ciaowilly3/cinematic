package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.TroupeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TroupeMemberRepository extends JpaRepository<TroupeMember, Long> {
}
