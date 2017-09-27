/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.structures
 * File name: Guid.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.UCHAR;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.USHORT;

//TODO: Auto-generated Javadoc
/**
* Ported from Guid.h. Microsoft Windows SDK 6.0A.
* 
* @author dblock[at]dblock.org
*/
public interface Guid {

   /** The Constant IID_NULL. */
   public final static IID IID_NULL = new IID();

   /**
    * The Class GUID.
    *
    * @author Tobias Wolf, wolf.tobias@gmx.net
    */
   public static class GUID extends Structure {
   	
       /**
        * The Class ByReference.
        *
        * @author Tobias Wolf, wolf.tobias@gmx.net
        */
       public static class ByReference extends GUID implements
               Structure.ByReference {

           /**
            * Instantiates a new by reference.
            */
           public ByReference() {
           }

           /**
            * Instantiates a new by reference.
            * 
            * @param guid
            *            the guid
            */
           public ByReference(GUID guid) {
               super(guid.getPointer());

               Data1 = guid.Data1;
               Data2 = guid.Data2;
               Data3 = guid.Data3;
               Data4 = guid.Data4;
           }

           /**
            * Instantiates a new by reference.
            * 
            * @param memory
            *            the memory
            */
           public ByReference(Pointer memory) {
               super(memory);
           }
       }

       /** The Data1. */
       public ULONG Data1;

       /** The Data2. */
       public USHORT Data2;

       /** The Data3. */
       public USHORT Data3;

       /** The Data4. */
       public UCHAR[] Data4 = new UCHAR[8];

       /**
        * Instantiates a new guid.
        */
       public GUID() {
       }

       /**
        * Instantiates a new guid.
        * 
        * @param guid
        *            the guid
        */
       public GUID(GUID guid) {
           this.Data1 = guid.Data1;
           this.Data2 = guid.Data2;
           this.Data3 = guid.Data3;
           this.Data4 = guid.Data4;

           this.writeFieldsToMemory();
       }

       /**
        * Instantiates a new guid.
        * 
        * @param memory
        *            the memory
        */
       public GUID(Pointer memory) {
           super(memory);
           read();
       }

       /*
        * (non-Javadoc)
        * 
        * @see com.sun.jna.Structure#getFieldOrder()
        */
       protected List<String> getFieldOrder() {
           return Arrays.asList(new String[] { "Data1", "Data2", "Data3",
                   "Data4" });
       }

       /**
        * Write fields to backing memory.
        */
       protected void writeFieldsToMemory() {
           this.writeField("Data1");
           this.writeField("Data2");
           this.writeField("Data3");
           this.writeField("Data4");
       }
   }

   /**
    * The Class CLSID.
    *
    * @author Tobias Wolf, wolf.tobias@gmx.net
    */
   public static class CLSID extends GUID {

       /**
        * The Class ByReference.
        *
        * @author Tobias Wolf, wolf.tobias@gmx.net
        */
       public static class ByReference extends GUID {

           /**
            * Instantiates a new by reference.
            */
           public ByReference() {
           }

           /**
            * Instantiates a new by reference.
            * 
            * @param guid
            *            the guid
            */
           public ByReference(GUID guid) {
               super(guid);
           }
            
           /**
            * Instantiates a new by reference.
            * 
            * @param memory
            *            the memory
            */
           public ByReference(Pointer memory) {

           }
       }

       /**
        * Instantiates a new clsid.
        */
       public CLSID() {
       }
       
       /**
        * Instantiates a new clsid.
        *
        * @param guid the guid
        */
       public CLSID(GUID guid) {
           super(guid);
       }
   }

   /**
    * The Class REFIID.
    *
    * @author Tobias Wolf, wolf.tobias@gmx.net
    */
   public class REFIID extends IID {

       /**
        * Instantiates a new refiid.
        */
       public REFIID() {
           // TODO Auto-generated constructor stub
       }

       /**
        * Instantiates a new refiid.
        * 
        * @param memory
        *            the memory
        */
       public REFIID(Pointer memory) {
           super(memory);
           // TODO Auto-generated constructor stub
       }
   }

   /**
    * The Class IID.
    *
    * @author Tobias Wolf, wolf.tobias@gmx.net
    */
   public class IID extends GUID {

       /**
        * Instantiates a new iid.
        */
       public IID() {
           // TODO Auto-generated constructor stub
       }

       /**
        * Instantiates a new iid.
        * 
        * @param memory
        *            the memory
        */
       public IID(Pointer memory) {
           super(memory);
           // TODO Auto-generated constructor stub
       }
   }
}
