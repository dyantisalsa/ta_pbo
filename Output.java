import javax.swing.JTextArea;

public class Output {
    private JTextArea textArea;
    public Output(JTextArea textArea) {
        this.textArea = textArea;
    }
    public void append(String text) {
        textArea.append(text);
    }
}