import org.springframework.beans.factory.annotation.Autowired;
import com.example.makeup_booking_app.Entity.WorkSchedule;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.makeup_booking_app.Services.WorkScheduleService;
import com.example.makeup_booking_app.Entity.WorkSchedule;
@RestController
@RequestMapping("/api/schedules")
//@RequiredArgsConstructor
public class WorkScheduleController {
    private final WorkScheduleService service;

    @Autowired
    public WorkScheduleController(WorkScheduleService service) {
        this.service = service;
    }

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
