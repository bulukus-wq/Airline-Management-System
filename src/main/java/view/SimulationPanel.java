package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SimulationPanel extends JPanel {
    private MainFrame frame;

    private JPanel seatGridPanel;
    private JPanel[][] seatVisuals;
    private JCheckBox chkSync;
    private JButton btnStart;
    private JLabel lblResult;
    private JProgressBar progressBar;

    private static final int ROWS = 30;
    private static final int COLS = 6;
    private static final int TOTAL_SEATS = 180;
    private static final int PASSENGER_COUNT = 90;

    private boolean[][] seatData;
    private int occupiedCount = 0;

    public SimulationPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Üst panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));

        chkSync = new JCheckBox("Senkronize Calistir (Guvenli Mod)");
        chkSync.setSelected(true); 
        chkSync.setFont(new Font("Arial", Font.BOLD, 14));

        btnStart = new JButton("Simulasyonu Başlat");
        btnStart.setPreferredSize(new Dimension(160, 30));
        btnStart.setBackground(new Color(60, 179, 113));
        btnStart.setForeground(Color.WHITE);

        JButton btnBack = new JButton("Geri Don");
        btnBack.addActionListener(e -> frame.showScreen("BOOKING")); 

        topPanel.add(chkSync);
        topPanel.add(btnStart);
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // Koltuk düzeni - Aralardaki boşluğu 5px yaparak daha ferah bir görüntü sağladık
        seatGridPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));
        seatVisuals = new JPanel[ROWS][COLS];
        seatData = new boolean[ROWS][COLS];

        initGrid();

        // ÖNEMLİ DEĞİŞİKLİK: Grid'in yayılmasını önlemek için FlowLayout kullanan bir sarmalayıcı panel
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapperPanel.add(seatGridPanel);

        // Kaydırma paneli wrapperPanel'i sarmalıyor
        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Daha akıcı kaydırma için
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ucak Koltuk Durumu "));
        add(scrollPane, BorderLayout.CENTER);

        // Alt panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        progressBar = new JProgressBar(0, PASSENGER_COUNT);
        progressBar.setStringPainted(true);

        lblResult = new JLabel("Durum: Baslamaya Hazir | Senaryo: 90 Yolcu", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        bottomPanel.add(progressBar, BorderLayout.NORTH);
        bottomPanel.add(lblResult, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnStart.addActionListener(e -> startSimulation());
    }

    private void initGrid() {
        seatGridPanel.removeAll();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel p = new JPanel();
                // ÖNEMLİ DEĞİŞİKLİK: Kareye yakın (En: 45, Boy: 40) sabit boyut verdik
                p.setPreferredSize(new Dimension(45, 40)); 
                p.setBackground(new Color(144, 238, 144)); 
                p.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                p.setToolTipText("Koltuk: " + (i + 1) + "-" + (char) ('A' + j));

                seatVisuals[i][j] = p;
                seatGridPanel.add(p);
            }
        }
        seatGridPanel.revalidate();
        seatGridPanel.repaint();
    }

    private void startSimulation() {
        resetSimulation();

        btnStart.setEnabled(false);
        lblResult.setText("Simulasyon çalisiyor...");
        lblResult.setForeground(Color.BLACK);

        new Thread(() -> {
            boolean isSync = chkSync.isSelected();
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < PASSENGER_COUNT; i++) {
                Thread t = new Thread(new PassengerTask(isSync));
                threads.add(t);
                t.start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            SwingUtilities.invokeLater(() -> {
                for (int r = 0; r < ROWS; r++) {
                    for (int c = 0; c < COLS; c++) {
                        if (seatData[r][c] == true) {
                            seatVisuals[r][c].setBackground(Color.RED);
                            progressBar.setValue(progressBar.getValue() + 1);
                            occupiedCount++;
                        }
                    }
                }

                double ratio = ((double) occupiedCount / TOTAL_SEATS) * 100;
                String resultText = String.format("Dolu Koltuk: %d / %d (Doluluk Orani: %.1f%%)",
                        occupiedCount, TOTAL_SEATS, ratio);
                lblResult.setText(resultText);

                if (occupiedCount != PASSENGER_COUNT) {
                    lblResult.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(this,
                            "Race Condition Olustu!\n" +
                                    "90 yolcu vardi ama " + occupiedCount + " koltuk doldu.\n" +
                                    "Asenkronizasyondan kaynakli threadler cakisti.",
                            "Test Sonucu", JOptionPane.WARNING_MESSAGE);
                } else {
                    lblResult.setForeground(new Color(0, 100, 0));
                }

                btnStart.setEnabled(true);
            });

        }).start();
    }

    private void resetSimulation() {
        occupiedCount = 0;
        seatData = new boolean[ROWS][COLS];
        progressBar.setValue(0);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seatVisuals[i][j].setBackground(new Color(144, 238, 144));
            }
        }
    }

    class PassengerTask implements Runnable {
        private boolean useSync;
        private Random random = new Random();

        public PassengerTask(boolean useSync) {
            this.useSync = useSync;
        }

        @Override
        public void run() {
            if (useSync) {
                bookSeatSynchronized();
            } else {
                bookSeatUnsafe();
            }
        }

        private void bookSeatSynchronized() {
            synchronized (SimulationPanel.this) {
                tryBookSeat();
            }
        }

        private void bookSeatUnsafe() {
            tryBookSeat();
        }

        private void tryBookSeat() {
            boolean seated = false;
            while (!seated) {
                int r = random.nextInt(ROWS);
                int c = random.nextInt(COLS);

                if (!seatData[r][c]) {
                    if (!useSync) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                        }
                    }
                    seatData[r][c] = true;
                    seated = true;
                }
            }
        }
    }
}