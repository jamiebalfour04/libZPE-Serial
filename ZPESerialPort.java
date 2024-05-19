import java.util.HashMap;

import com.fazecast.jSerialComm.SerialPort;

import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.interfaces.ZPEPropertyWrapper;

public class ZPESerialPort extends ZPEObject {

  private static final long serialVersionUID = -2658403322308184479L;
  SerialPort p = null;

  public ZPESerialPort(ZPERuntimeEnvironment z, ZPEPropertyWrapper parent, String name) {
    super(z, parent, name);
    addNativeMethod("get_name", new get_name_Command());
    addNativeMethod("is_open", new is_open_Command());
    addNativeMethod("open", new open_Command());
    addNativeMethod("write", new write_Command());
  }

  public class get_name_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {
      return p.getDescriptivePortName();
    }

    @Override
    public String[] getParameterNames() {
      return new String[0];
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 3;
    }

    @Override
    public String getName() {
      return "get_name";
    }

  }

  public class is_open_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {
      return p.isOpen();
    }

    @Override
    public String[] getParameterNames() {
      return new String[0];
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 3;
    }

    @Override
    public String getName() {
      return "is_open";
    }

  }

  public class open_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {
      return p.openPort();
    }

    @Override
    public String[] getParameterNames() {
      return new String[0];
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 3;
    }

    @Override
    public String getName() {
      return "open";
    }

  }

  public class write_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {
      byte b = (byte) 3072;
      p.setBaudRate(19200);
      p.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
      p.writeBytes(new byte[] {b}, 1);
      p.closePort();
      return null;
    }

    @Override
    public String[] getParameterNames() {
      return new String[0];
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 3;
    }

    @Override
    public String getName() {
      return "write";
    }

  }


}
