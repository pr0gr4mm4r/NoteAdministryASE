package application.notes.crud.declare.abstraction;

import java.io.IOException;

public interface HeaderAdder {
    void addHeader(HeaderInformation headerInformation) throws IOException;
}
