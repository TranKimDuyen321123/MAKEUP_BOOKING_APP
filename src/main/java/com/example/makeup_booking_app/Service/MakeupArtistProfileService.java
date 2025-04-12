@Service
@RequiredArgsConstructor
public class MakeupArtistProfileService {
    private final MakeupArtistProfileRepository repository;

    public List<MakeupArtistProfile> getAll() {return repository.findAll();}

    public List<MakeupArtistProfile> getByBranch(Long branchId) {
        return repository.findByBranchId(branchId);
    }

    public MakeupArtistProfile create(MakeupArtistProfile artist) {
        return repository.save(artist);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Makeup Artist Profile not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
