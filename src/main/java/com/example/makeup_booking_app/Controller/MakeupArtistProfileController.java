@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class MakeupArtistProfileController {
    private final MakeupArtistProfileService service;

    @GetMapping
    public ResponseEntity<List<MakeupArtistProfile>> getAll() {
        return ResponseEntity.ok(service.getAll());}

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<MakeupArtistProfile>> getByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(service.getByBranch(branchId));}

    @PostMapping
    public ResponseEntity<MakeupArtistProfile> create(@RequestBody MakeupArtistProfile artist) {
        return ResponseEntity.ok(service.create(artist));}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();}
}
