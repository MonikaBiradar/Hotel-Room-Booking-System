package hotelroombookingsystem;

class Room
{
    private int roomNum;
    private String roomType;
    private double roomPrice;
    private boolean isAvailable;
    
    public Room(int roomNum,String roomType)
    {
        this.roomNum=roomNum;
        this.roomType=roomType;
        if("Single".equalsIgnoreCase(roomType)){roomPrice=1200;}
        if("Double".equalsIgnoreCase(roomType)){roomPrice=1800;}
        if("Suite".equalsIgnoreCase(roomType)){roomPrice=2400;}
        isAvailable=true;
    }
    
    public int getRoomNum()
    {
        return roomNum;
    }
    public String getRoomType()
    {
        return roomType;
    }
    public double getRoomPrice()
    {
        return roomPrice;
    }
    public boolean isAvailable()
    {
        return isAvailable;
    }
    
    public boolean book()
    {
        if(!isAvailable)
            return false;
        isAvailable=false;
        return true;
    }
    
    public boolean cancel()
    {
        if(isAvailable)
           return false;
        isAvailable=true;
        return true;
    }
    
    public String toString()
    {
        return "Room No.: "+roomNum+
                ", Room Type: "+roomType+
                ", Price/day: "+roomPrice+
                ", Availability: "+isAvailable;
    }
}
class Customer
{
    private int customerId;
    private String customerName;
    
    public Customer(int customerId,String customerName)
    {
        this.customerId=customerId;
        this.customerName=customerName;
    }
    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public String toString()
    {
        return "Customer Id: "+customerId+
                ", Customer Name: "+customerName;
    }
}

enum BookingStatus
{
    BOOKED,
    CANCELLED
}

class Booking
{
    private int bookingId;
    private BookingStatus status;
    private int numOfNights;
    private Room room;
    private Customer customer;
    
    private static int Id =100;
    
    public Booking(Room room,Customer customer,int numOfNights)
    {
        this.bookingId=Id++;
        this.customer=customer;
        this.room=room;
        this.numOfNights=numOfNights;
        status=BookingStatus.BOOKED;
    }
    
    public int getBookingId(){return bookingId;}
    public Customer getCustomer(){return customer;}
    public Room getRoom(){return room;}
    
    public BookingStatus getStatus()
    {
        return status;
    }
    
    public void cancel()
    {
        status=BookingStatus.CANCELLED;
    }
        
    public double getTotalPrice()
    {
        return (room.getRoomPrice()* numOfNights);
    }
    
    public String toString()
    {
        return "Booking Id: "+bookingId+
                ", Customer name: "+customer.getCustomerName()+
                ", Room No.: "+room.getRoomNum()+
                ", No. of Nights: "+numOfNights+
                ", Status: "+status;
    }
}

class Hotel
{
    Room[] room = new Room[100];
    Customer[] customer = new Customer[500];
    Booking[] booking = new Booking[500];
    int roomCount=0;
    int custCount=0;
    int bookCount=0;
    
    public void addRoom(int roomNum,String roomType)
    {
        Room data = searchRoom(roomNum);
        if(data!=null)
        {
            System.out.println("Room Already Exists.");
            return;
        }
        
        room[roomCount]= new Room(roomNum,roomType);
        roomCount++;
    }
    
    public Room searchRoom(int roomNum)
    {
        for(int i=0;i<roomCount;i++)
        {
            if(roomNum==room[i].getRoomNum())
            {
                return room[i];
            }
        }
        return null;
    }
    public void displayAllRoom()
    {
        for(int i=0;i<roomCount;i++)
        {
            System.out.println(room[i]);
        }
    }
    
    public void displayAvailableRooms()
    {
        for(int i=0;i<roomCount;i++)
        {
            if(room[i].isAvailable())
                System.out.println(room[i]);
        }
    }
    
    public void addCustomer(int customerId, String customerName)
    {
        Customer cust = searchCustomer(customerId);
        if(cust!=null)
        {
            System.out.println("Customer Already Exists.");
            return;
        }
        
        customer[custCount]= new Customer(customerId,customerName);
        custCount++;
    }
    public Customer searchCustomer(int customerId)
    {
        for(int i=0;i<custCount;i++)
        {
            if(customerId==customer[i].getCustomerId())
            {
                return customer[i];
            }
        }
        return null;
    }
    public void bookRoom(int roomNum,int customerId,int totalNights)
    {
        Room room = searchRoom(roomNum);
        if(room==null)
        {
            System.out.println("Room Not Found.");
            return;
        }
        
        Customer customer = searchCustomer(customerId);
        if(customer==null)
        {
            System.out.println("Customer Not Found.");
            return; 
        }
        
        for(int i=0;i<bookCount;i++)
        {
            if(booking[i].getCustomer()==customer && booking[i].getRoom()==room)
            {
                if(booking[i].getStatus()== BookingStatus.BOOKED)
                {
                    System.out.println("Customer already has this room booked.");
                    return;
                }
            }
        }
        
        if(!room.book())
            System.out.println("Room Not Available.");
        else
        {
            booking[bookCount]= new Booking(room,customer,totalNights);
            bookCount++;
            System.out.println("Room Booked.");
        }
        
        
    }
    
    public void cancelBooking(int bookingId)
    {
        Booking booking = searchBooking(bookingId);
        if(booking==null)
        {
            System.out.println("Booking Not Found.");
            return;
        }
        if(booking.getStatus()==BookingStatus.CANCELLED)
        {
            
            System.out.println("Booking Already Cancelled.");
            return;
        }
        booking.getRoom().cancel();
        booking.cancel();
        System.out.println("Booking Cancelled.");
        
    }
    public Booking searchBooking(int bookingId)
    {
        for(int i=0;i<bookCount;i++)
        {
            if(bookingId==booking[i].getBookingId())
            {
                return booking[i];
            }
        }
        return null;
    }
    public void displayCustomerBooking(int customerId)
    {
        boolean found=false;
        Customer customer = searchCustomer(customerId);
        if(customer==null)
        {
            System.out.println("Customer Not Found.");
            return; 
        }
        
        for(int i=0;i<bookCount;i++)
        {
            if(booking[i].getCustomer()==customer)
            {
                System.out.println(booking[i]);
                found=true;
            }
        }
        if(!found)
        {
            System.out.println("No Bookings for this Customer.");
        }
    }
    public void displayAllBookings()
    {
        for(int i=0;i<bookCount;i++)
        {
            System.out.println(booking[i]);
        }
    }
    
    public void getBookingPrice(int bookingId)
    {
        Booking booking = searchBooking(bookingId);
        if(booking==null)
        {
            System.out.println("Booking Not Available.");
            return;
        }
        System.out.println("Total Booking Price: Rs."+booking.getTotalPrice());
    }
}

public class HotelRoomBookingSystem {

    public static void main(String[] args) 
    {
        Hotel hotel = new Hotel();
        hotel.addRoom(10,"Single");
        hotel.addRoom(12,"Double");
        hotel.addRoom(15,"Suite");
        hotel.addRoom(16,"Single");
        
        hotel.addCustomer(101, "Tom");
        hotel.addCustomer(102, "Rob");
        hotel.addCustomer(103, "Smith");
        hotel.addCustomer(104, "Daniel");
        
        hotel.bookRoom(12, 102, 3);
        hotel.bookRoom(15, 103, 2);
        hotel.bookRoom(16, 104, 4);
        
        hotel.displayAllBookings();
        
        hotel.cancelBooking(101);
        
        hotel.displayAllBookings();
        hotel.cancelBooking(101);
        
    }
    
}
