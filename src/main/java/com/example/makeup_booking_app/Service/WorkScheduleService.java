@Service
@RequiredArgsConstructor
public class WorkScheduleService {
    private final WorkScheduleRepository repository;
    private final MakeupArtistProfileRepository artistRepository;

    public List<WorkSchedule> getAll() {
        return repository.findAll();
    }

    public WorkSchedule create(Long artistId, WorkSchedule schedule) {
        MakeupArtistProfile artist = artistRepository.findById(artistId).orElseThrow(() -> new EntityNotFoundException("Makeup Artist not found with ID: " + artistId));
        schedule.setArtist(artist);
        return repository.save(schedule);}

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Work Schedule not found with ID: " + id);}
        repository.deleteById(id);}
}
