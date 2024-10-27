import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class PenentuJumlahHari extends JFrame {
    // Deklarasi komponen
    private JLabel lblYear, lblMonth, lblResult;
    private JTextField txtYear;
    private JComboBox<String> cmbMonth;
    private JButton btnHitung, btnHapus, btnSimpan, btnKeluar;
    private JLabel lblDaysResult;
    private JPanel outerPanel, panelInput, panelResult, panelButtons;

    // Objek untuk kelas HitungHari
    private HitungHari hitungHari = new HitungHari();

    public PenentuJumlahHari() {
        // Set judul dan layout
        setTitle("Aplikasi Penentu Jumlah Hari");
        setLayout(new BorderLayout());

        // Panel outer layer
        outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin luar
        add(outerPanel);

        // Panel untuk input (tahun dan bulan)
        panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 3, 5, 5)); // Grid 2x2 untuk input
        panelInput.setBackground(new Color(144, 250, 178)); // Background hijau untuk input tahun dan bulan
        outerPanel.add(panelInput, BorderLayout.NORTH);

        // Inisialisasi komponen input
        lblYear = new JLabel("Tahun");
        txtYear = new JTextField();
        lblMonth = new JLabel("Bulan");
        cmbMonth = new JComboBox<>(new String[]{"Januari", "Februari", "Maret", "April", 
                                                "Mei", "Juni", "Juli", "Agustus", 
                                                "September", "Oktober", "November", "Desember"});

        // Tambahkan komponen ke panel input
        panelInput.add(lblYear);
        panelInput.add(txtYear);
        panelInput.add(lblMonth);
        panelInput.add(cmbMonth);

        // Panel untuk hasil
        panelResult = new JPanel();
        panelResult.setBackground(new Color(255, 253, 208)); // Background cream untuk hasil
        panelResult.setLayout(new GridBagLayout()); // Use GridBagLayout to center the label
        outerPanel.add(panelResult, BorderLayout.CENTER);

        // Label hasil
        lblDaysResult = new JLabel("Hasil akan ditampilkan di sini", JLabel.CENTER);
        lblDaysResult.setOpaque(true);
        lblDaysResult.setBackground(new Color(255, 253, 208)); // Cream for label background

        // Adjust size for longer texts
        lblDaysResult.setPreferredSize(new Dimension(350, 60)); // Make the label wider and taller

        // Set text alignment to make sure it's centered
        lblDaysResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblDaysResult.setVerticalAlignment(SwingConstants.CENTER);

        // Add result label to the panel
        panelResult.add(lblDaysResult);

        // Panel untuk tombol
        panelButtons = new JPanel();
        panelButtons.setBackground(new Color(255, 182, 193)); // Background pink untuk tombol
        panelButtons.setLayout(new FlowLayout());
        outerPanel.add(panelButtons, BorderLayout.SOUTH);

        // Inisialisasi tombol
        btnHitung = new JButton("Hitung");
        btnHapus = new JButton("Hapus");
        btnSimpan = new JButton("Simpan");
        btnKeluar = new JButton("Keluar");

        // Tambahkan tombol ke panel tombol
        panelButtons.add(btnHitung);
        panelButtons.add(btnHapus);
        panelButtons.add(btnSimpan);
        panelButtons.add(btnKeluar);

        // Aksi tombol Hitung
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungJumlahHari();
            }
        });

        // Aksi tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusForm();
            }
        });

        // Aksi tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanHasil();
            }
        });

        // Aksi tombol Keluar
        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ukuran frame
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Metode untuk memanggil perhitungan jumlah hari dari kelas HitungHari
    private void hitungJumlahHari() {
        try {
            int year = Integer.parseInt(txtYear.getText());
            int month = cmbMonth.getSelectedIndex() + 1;
            int days = hitungHari.hitung(year, month);

            // Tampilkan hasil di label
            lblDaysResult.setText("Jumlah hari pada bulan " + cmbMonth.getSelectedItem() + 
                                  " tahun " + year + " adalah " + days);
            
            // Refresh GUI agar perubahan terlihat
            lblDaysResult.revalidate();
            lblDaysResult.repaint();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input tahun harus berupa angka!", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metode untuk menghapus form
    private void hapusForm() {
        txtYear.setText("");
        cmbMonth.setSelectedIndex(0);
        lblDaysResult.setText("Hasil akan ditampilkan di sini");
    }

    // Metode untuk menyimpan hasil ke file
    private void simpanHasil() {
        try (FileWriter writer = new FileWriter("hasil_perhitungan.txt", true)) {
            writer.write(lblDaysResult.getText() + "\n");
            JOptionPane.showMessageDialog(this, "Hasil berhasil disimpan!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error menyimpan file!", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method
    public static void main(String[] args) {
        new PenentuJumlahHari();
    }
}
