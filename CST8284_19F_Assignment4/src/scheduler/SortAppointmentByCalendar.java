
package scheduler;

import java.util.Comparator;

/**
 * Class which implements Comparator interface used to compare elements of objects
 * Help to Sort array of Appointments 
 * @version 1.0
 * @author Dhillon Sahilvir
 */
public class SortAppointmentByCalendar implements Comparator<Appointment>{

    /**
     * Override function of Comparator Interface used to compare element of two objects based on Calender date 
     * @param o1 Object of Appointment class used in comparison
     * @param o2 Object of Appointment class
     * @return integer representing whether object1 calendar date 
     * is equal(0), greater(1) or lesser(-1) then object2 calender date
     */
    @Override
    public int compare(Appointment o1, Appointment o2) {
        if(o1.getCalendar().equals(o2.getCalendar())){
            return 0;
        }
        else if(o1.getCalendar().before(o2.getCalendar()))
        {
            return -1;
        }
        else if(o1.getCalendar().after(o2.getCalendar()))
        {
            return 1;
        }
        return 0;
    }
}
