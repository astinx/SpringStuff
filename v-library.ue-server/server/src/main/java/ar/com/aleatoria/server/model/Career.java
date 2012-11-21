
package ar.com.aleatoria.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Career {

    @Id
    private Integer id;

    private String name;
}
