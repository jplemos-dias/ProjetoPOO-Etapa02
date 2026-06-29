public class HorarioDisponivel {
    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana, String turno) {
        this.diaSemana = diaSemana;
        this.turno = turno;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public String exibirResumo() {
        return diaSemana + " - " + turno;
    }
}
