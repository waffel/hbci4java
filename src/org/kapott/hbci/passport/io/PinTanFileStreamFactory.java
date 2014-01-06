package org.kapott.hbci.passport.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.kapott.hbci.manager.HBCIUtils;

/**
 * Diese Implementierung legt die Daten in der Datei ab, die in der Konfiguration unter <code>client.passport.PinTan.filename</code> eingestellt ist.
 *
 * @author Hendrik Schnepel
 */
public class PinTanFileStreamFactory implements ResourceStreamFactory {

    private String fname;

    public PinTanFileStreamFactory() {
        this(HBCIUtils.getParam("client.passport.PinTan.filename"));
    }

    public PinTanFileStreamFactory(String fname) {
        this.fname = fname;
        if (fname == null) {
            throw new NullPointerException("file name (client.passport.PinTan.filename?) must not be null");
        }
        HBCIUtils.log("loading passport data from file "+fname,HBCIUtils.LOG_DEBUG);
    }

    @Override
    public InputStream newInputStream() throws IOException {
        return new FileInputStream(fname);
    }

    @Override
    public OutputStream newOutputStream() throws IOException {
        return TemporaryFileOutputStream.create(new File(fname));
    }

    @Override
    public URI getURI() {
        return new File(fname).toURI();
    }

}
