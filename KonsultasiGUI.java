import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class KonsultasiGUI {
    private JFrame frame;
    private Pasien pasien;
    private JTextArea outputTextArea;
    private JTextField chatInputField;
    private JTextArea riwayatTextArea;

    public KonsultasiGUI() {

        frame = new JFrame("Aplikasi Konsultasi Psikiater");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        riwayatTextArea = new JTextArea();
        riwayatTextArea.setEditable(false);
        JScrollPane riwayatScrollPane = new JScrollPane(riwayatTextArea);
        outputPanel.add(riwayatScrollPane, BorderLayout.EAST);

        JPanel inputPanel = new JPanel(new BorderLayout());
        chatInputField = new JTextField();
        JButton sendButton = new JButton("Send");

        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        riwayatTextArea.setLineWrap(true);
        riwayatTextArea.setWrapStyleWord(true);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendChatMessage();
            }
        });
        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        JButton startButton = new JButton("Mulai Konsultasi");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startConsultation();
            }
        });
        frame.getContentPane().add(startButton, BorderLayout.NORTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    private void sendChatMessage() {
        String chatMessage = chatInputField.getText();
        if (!chatMessage.isEmpty()) {
            pasien.kirimPesan(chatMessage, pasien.getPsikiater());
            outputTextArea.append("\nYou : " + chatMessage + "\n");

            Psikiater dokter = pasien.getPsikiater();
            String dokterResponse = "Terima kasih atas pesan Anda. Jika anda ingin berkonsultasi lebih lanjut silahkan membuat jadwal dengan dokter yang bersangkutan. TerimaKasih.";
            dokter.kirimPesan(dokterResponse, pasien);
            outputTextArea.append(dokter.getNama() + " : " + dokterResponse + "\n");

            pasien.tambahRiwayatKonsultasi("Pasien: " + chatMessage);
            pasien.tambahRiwayatKonsultasi(dokter.getNama() + ": " + dokterResponse);

            updateRiwayatKonsultasi();

            chatInputField.setText("");
        }
    }
    private void startConsultation() {
        String namaPasien = JOptionPane.showInputDialog(frame, "Masukkan Nama Pasien:");
        int usiaPasien = Integer.parseInt(JOptionPane.showInputDialog(frame, "Masukkan Usia Pasien:"));
        String keluhanPasien = JOptionPane.showInputDialog(frame, "Masukkan Keluhan Pasien:");

        String namaDokter = "";
        Psikiater psikiater;
        if (usiaPasien >= 1 && usiaPasien <= 17) {
            psikiater = new PsikiaterAnak("Dr. Salsabiela (Spesialis Anak)");
            namaDokter = "Dr. Salsabiela";
        } else {
            psikiater = new PsikiaterDewasa("Dr. Hafidz");
            namaDokter = "Dr. Hafidz";
        }
        outputTextArea.append("Anda akan berkonsultasi dengan Psikiater " + namaDokter + ".\n");

        pasien = new Pasien(namaPasien, usiaPasien, keluhanPasien, psikiater);

        outputTextArea.append("Sesi konsultasi dimulai untuk " + pasien.getNama() + ":\n");
        pasien.mulaiKonsultasi();
        pasien.lihatObrolan(new Output(outputTextArea));
        pasien.lihatRiwayatKonsultasi(new Output(outputTextArea));

        updateRiwayatKonsultasi();
    }
    private void updateRiwayatKonsultasi() {
        riwayatTextArea.setText("Riwayat Konsultasi:\n" + pasien.getRiwayatKonsultasi());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KonsultasiGUI();
            }
        });
    }
}
