@Entity
@Table(name = "work_schedule")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private MakeupArtistProfile artist;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime time;

    @Column(name = "client_name")
    private String clientName;

    @Lob
    @Column(name = "note")
    private String note;

    // Getters and Setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public MakeupArtistProfile getArtist() {return artist;}

    public void setArtist(MakeupArtistProfile artist) {this.artist = artist;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public LocalTime getTime() {return time;}

    public void setTime(LocalTime time) {this.time = time;}

    public String getClientName() {return clientName;}

    public void setClientName(String clientName) {this.clientName = clientName;}

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}
}
