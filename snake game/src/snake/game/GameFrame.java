/*
 * GameFrame.java
 *
 * Created on 24 February 2022, 8:48 PM
 */
package snake.game;

import javax.management.*;
import java.util.Arrays;

/**
 * Class GameFrame
 *
 * @author dbanz
 */
public class GameFrame extends StandardMBean implements GameFrameMBean {

    private SnakeGame theRef;

    public GameFrame(SnakeGame theRef) throws NotCompliantMBeanException {
        //WARNING Uncomment the following call to super() to make this class compile (see BUG ID 122377)
        // super(GameFrameMBean.class);
        this.theRef = theRef;
    }
    
    
    @Override
    public MBeanInfo getMBeanInfo() {
        MBeanInfo mbinfo = super.getMBeanInfo();
        return new MBeanInfo(mbinfo.getClassName(),
                mbinfo.getDescription(),
                mbinfo.getAttributes(),
                mbinfo.getConstructors(),
                mbinfo.getOperations(),
                getNotificationInfo());
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{};
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanInfo info) {
        return "GameFrame Description";
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanAttributeInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanAttributeInfo info) {
        String description = null;
        return description;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanParameterInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("main")) {
            switch (sequence) {
                case 0:
                    return "";
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanParameterInfo.getName()
     */
    @Override
    protected String getParameterName(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("main")) {
            switch (sequence) {
                case 0:
                    return "param0";
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanOperationInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanOperationInfo info) {
        String description = null;
        MBeanParameterInfo[] params = info.getSignature();
        String[] signature = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            signature[i] = params[i].getType();
        }
        String[] methodSignature;
        methodSignature = new String[]{java.lang.String[].class.getName()};
        if (info.getName().equals("main") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        return description;
    }

    /**
     * Operation exposed for management
     * @param param0
     */
    public void main(String[] param0) {
        theRef.main(param0);
    }
}
