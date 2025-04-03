@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class WorkScheduleController {
    private final WorkScheduleService service;

    @GetMapping
    public ResponseEntity<List<WorkSchedule>> getAll() {
        return ResponseEntity.ok(service.getAll());}

    @PostMapping("/{artistId}")
    public ResponseEntity<WorkSchedule> create(@PathVariable Long artistId, @RequestBody WorkSchedule schedule) {
        return ResponseEntity.ok(service.create(artistId, schedule));}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();}
}
