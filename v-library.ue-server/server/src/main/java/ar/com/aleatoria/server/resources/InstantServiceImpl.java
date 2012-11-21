
package ar.com.aleatoria.server.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.aleatoria.server.db.InstantDAO;
import ar.com.aleatoria.server.model.Career;
import ar.com.aleatoria.server.model.Instant;

import com.google.gson.Gson;

@Controller
@RequestMapping("instant")
public class InstantServiceImpl {
    @Autowired
    InstantDAO instantDao;

    /**
     * @return json.
     * @uri http://localhost:8080/server/instant/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Instant[] getAllInstants() {
    	return instantDao.getAll().toArray(new Instant[instantDao.getAll().size()]);
    }

    /**
     * @param newInstant: The new instant to add to the database.
     * @uri http://localhost:8080/server/instant/
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addInstant(@RequestBody
    Instant newInstant) {
        instantDao.saveInstant(newInstant);
    }

    /**
     * @param instantname: The instantname of the instant.
     * @uri http://localhost:8080/server/instant/{id}
     */
    @RequestMapping(value = "/{instantname}")
    public @ResponseBody
    Instant getInstantById(@PathVariable
    String instantname) {
        return instantDao.getInstantById(instantname);
    }

    /**
     * @param newInstant: The newInstant to update.
     * @uri http://localhost:8080/server/instant/{newInstant}
     */
    @RequestMapping(value = "/{newInstant}", method = RequestMethod.PUT)
    public void updateInstant(@RequestBody
    Instant newInstant) {
        instantDao.saveInstant(newInstant);
    }

    /**
     * @param targetInstant: The targetInstant to delete.
     * @uri http://localhost:8080/server/instant/{targetInstant}
     */
    @RequestMapping(value = "/{targetInstant}", method = RequestMethod.DELETE)
    public void deleteInstantById(@PathVariable
    Instant targetInstant) {
        instantDao.delete(targetInstant);
    }

}
