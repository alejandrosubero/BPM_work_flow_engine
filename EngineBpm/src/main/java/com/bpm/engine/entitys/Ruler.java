package com.bpm.engine.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Ruler")
public class Ruler {

    @Id
    @GeneratedValue(generator = "sequence_id_task_ruler_generator")
    @SequenceGenerator(name = "sequence_id_task_ruler_generator", initialValue = 100, allocationSize = 2000)
    @Column(name = "idRuler", updatable = true, nullable = false, length = 25)
    private Long idRuler;
    private String condition;
    private String action;

    private String taskCode;


    public Ruler() {
    }

    public Long getIdRuler() {
        return idRuler;
    }

    public void setIdRuler(Long idRuler) {
        this.idRuler = idRuler;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruler ruler = (Ruler) o;
        return Objects.equals(idRuler, ruler.idRuler) && Objects.equals(condition, ruler.condition) && Objects.equals(action, ruler.action) && Objects.equals(taskCode, ruler.taskCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRuler, condition, action, taskCode);
    }
}
