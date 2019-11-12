
package core.shell.controller;

import core.model.Database;

/**
 *
 * @author lucas
 */
public interface ShellControllerInterface {

    public void textAreaResult(String txtVerification);
    public String getDBName();

    public void success(String message);

    public void failed(String message);

    public void showTable(Database banco);
    
}
