@Repository
public interface MakeupArtistProfileRepository extends JpaRepository<MakeupArtistProfile, Long> {
    List<MakeupArtistProfile> findByBranchId(Long branchId);
}

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {}
