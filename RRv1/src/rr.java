import java.util.*;

public class rr
{
    static ArrayList<Integer> times1 = new ArrayList<Integer>();
    static ArrayList<Integer> times2 = new ArrayList<Integer>();
    static ArrayList<Integer> times3 = new ArrayList<Integer>();
    static ArrayList<Integer> times4 = new ArrayList<Integer>();
    static ArrayList<Integer> times5 = new ArrayList<Integer>();																                                                                                  	//Әртүрлі процесті орындау уақыттарын сақтау үшін массив list жасамыз.

    static int contextSwitch = 0;
    static int timeCompleted = 0;
    static int length = 0;
    static int waitingTime = 0;
    public static void main(String []args)
    {

        Scanner scan1 = new Scanner(System.in);
        Random rng = new Random();

        System.out.println("Mynda biz round robin zhosparlaudy imitasyalaimyz");
        System.out.println("Siz qanwa procsesti iske qosqynyz keledi?: ");
        int x = scan1.nextInt();
        length = x;																													                                                                                   //орындалып жатқан процесстерге ұызындығын береді.

        System.out.println(x + " Procsestin oryndalu uaqyty.");

        for (int i = 0; i < x; i++) 																							                                                                                        	//Уақыт квантын тексеру үшін әртүрлі процесс уақыттарын рандомно  жасаймыз.
        {
            int processLength = rng.nextInt(100) + 1;
            times1.add(processLength); 																							                                                                                         	//Жасалған процестің орындау уақыттарын әртүрлі тізімдер массивінде сақтаймыз.
            times2.add(processLength);
            times3.add(processLength);
            times4.add(processLength);
            times5.add(processLength);

            System.out.println(times1.get(i));
        }

        System.out.println("Biz qazir bes turli uaqyt qvanttynyn procsesterin zhosparlaymyz.");
        System.out.println("\n");

        robin(5, times1);			//гамогенді жүйелер																			                                                                                //Жоспарлағышты 5 секундтық уақыт бөлігімен іске қосады.
        robin(10, times2);																										                                                                            	            //Жоспарлағышты 10 секундтық уақыт бөлігімен іске қосады.
        robin(15, times3);																											                                                                                        //Жоспарлағышты 15 секундтық уақыт бөлігімен іске қосады.
        robin(20, times4);																											                                                                                //Жоспарлағышты 20 секундтық уақыт бөлігімен іске қосады.
        robin(25, times5);																										                                                                                         	//Жоспарлағышты 25 секундтық уақыт бөлігімен іске қосады
    }

    public static void robin(int timeSlice, ArrayList<Integer> times)															                                                                                                      	//Уақыт тілімінің мәнін және процестің орындалу ұзақтығымен массив тізімін жібереді.
    {
        contextSwitch = 0;																											                                                                                                      //Әрбір робин үшін мәндерді 0-ге қалпына келтіреді.
        waitingTime = 0;
        timeCompleted = 0;

        for (int i = 0; i < times.size(); i++)																						                                                                                                    //Жоспарлауды аяқтауға арналған жалпы уақытты алады (процесті орындау уақыттарының сомасы).
        {
            timeCompleted += times.get(i);
        }

        while (times.size() > 0)																									                                                                                                  //Мәтінмәндік қосқыштардың санын санайды және күту уақытын есептейді.
        {
            int l = pop(times);
            l = (l - timeSlice);

            if (l > 0)
            {
                times.add(l);
            }

            waitingTime += timeSlice * (times.size() - 1);
            contextSwitch++;
        }
        System.out.println("Uaqyt aralyqy: " + timeSlice + " ms");
        report();
    }

    public static int pop(ArrayList<Integer> times)
    {
        int x = times.get(0);
        times.remove(0);
        return x;
    }

    public static long getAverageTurnAroundTime()
    {


        return timeCompleted/length;
    }

    public static float getThroughput()
    {

        return (float) length/(timeCompleted*contextSwitch-1);
    }

    public static long getAverageWaitTime()
    {
        return waitingTime/length;
    }


    public static void report()
    {
        System.out.println("Auystyrgyw: " + contextSwitch);
        System.out.println("Otkizu qabileti: " + getThroughput() + "s");
        System.out.println("Ortasha kutu uaqyty: " + getAverageWaitTime() + " ms");
        System.out.println("Ortasha ainalym uaqyty: " + getAverageTurnAroundTime() + " ms");
        System.out.println("\n");
    }
}