public class StopwatchActivity extends AppCompatActivity {

    private TextView textViewTimer;
    private Button startButton, stopButton, resetButton;
    private Handler handler;
    private long startTime, timeBuffer, updateTime = 0L;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        textViewTimer = findViewById(R.id.textViewTimer);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        handler = new Handler();

        startButton.setOnClickListener(view -> {
            if(!running) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                running = true;
            }
        });

        stopButton.setOnClickListener(view -> {
            if(running) {
                timeBuffer += updateTime;
                handler.removeCallbacks(runnable);
                running = false;
            }
        });

        resetButton.setOnClickListener(view -> {
            startTime = 0L;
            timeBuffer = 0L;
            updateTime = 0L;
            running = false;
            textViewTimer.setText(R.string.timer_default);
            handler.removeCallbacks(runnable);
        });
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            if(running) {
                updateTime = SystemClock.uptimeMillis() - startTime;
                long updatedTime = timeBuffer + updateTime;
                int secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                secs %= 60;
                int milliseconds = (int) (updatedTime % 1000);
                textViewTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%03d", mins, secs, milliseconds));
                handler.postDelayed(this, 0);
            }
        }
    };
}
