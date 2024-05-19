import jamiebalfour.zpe.core.YASSByteCodes;
import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.interfaces.ZPELibrary;
import com.fazecast.jSerialComm.SerialPort;
import jamiebalfour.zpe.types.ZPEList;

import java.util.HashMap;
import java.util.Map;

public class Plugin implements ZPELibrary {

  @Override
  public Map<String, ZPECustomFunction> getFunctions() {
    Map<String, ZPECustomFunction> m = new HashMap<String, ZPECustomFunction>();
    m.put("list_serial_ports", new ListSerialPorts());
    return m;
  }

  @Override
  public Map<String, Class<? extends ZPEStructure>> getObjects() {
    return null;
  }

  public class ListSerialPorts implements jamiebalfour.zpe.interfaces.ZPECustomFunction{

    @Override
    public Object MainMethod(HashMap<String, Object> hashMap, ZPERuntimeEnvironment zpeRuntimeEnvironment) {
      SerialPort[] ports = SerialPort.getCommPorts();
      ZPEList output = new ZPEList();
      for(SerialPort p : ports) {
        //Add all ports to list
        ZPESerialPort port = new ZPESerialPort(zpeRuntimeEnvironment, zpeRuntimeEnvironment.GetCurrentZPEFunction(), "ZPESerialPort");
        port.p = p;
        output.add(port);
      }

      return output;
    }

    @Override
    public String getManualEntry() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public String getManualHeader() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public int getMinimumParameters() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public String[] getParameterNames() {
      return new String[0];
    }

    @Override
    public int getRequiredPermissionLevel() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public byte getReturnType() {
      return YASSByteCodes.LIST;
    }

  }

  @Override
  public String getName() {
    return "libZPE-Serial";
  }

  @Override
  public String getVersionInfo() {
    return "1.0";
  }
}