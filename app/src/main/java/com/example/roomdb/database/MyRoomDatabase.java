package com.example.roomdb.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdb.database.dao.EmployeeDao;
import com.example.roomdb.database.dao.SalaryEmployeeDao;
import com.example.roomdb.database.model.Employee;
import com.example.roomdb.database.model.SalaryEmployee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



//new terms -> volatile - scyncronize - excutorService
// exportSchema -> تسمح إنك تصدر ال تيبل ك ملف جيسون
// volatile -> لما يكون بدك توصل للمتغير من أكثر من ثريد علشان يخليك تتعامل مع اخر قيمة للمتغير تم التعديل عليها من أي ثريد اخر
// excutorService -> بتعمل ليك عدد من الثريد اللي بدك بحيث تستخدمهم عند أي أمر على الداتابيز وبتستهلك مساحه ف الذاكره على جسب عدد الثريد اللي ه تنشئها وبتقدر ثريد استخدمتها قبل كدا لما تخلص شغلها ومش بتعمل دتروي للثريد
// syncronize -> تمنعك إنك تنفذ الكود اللي جواها من أكثر من ثريد ف نفس الوقت .. الثريد الأولى لما تخلص بعداها تشتغل الثريد التانيه
@Database(entities = {Employee.class, SalaryEmployee.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    abstract EmployeeDao getEmployeeDao();
    abstract SalaryEmployeeDao getSalaryEmployeeDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUM_OF_THREADS = 4;
    static final ExecutorService databaseWriteExcutor = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public static MyRoomDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, MyRoomDatabase.class,"employee_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // هينفذ ال كود اللي مكتوب هنا ف أول مره ينشيء الداتابيز
    // room.addCallBack(sRoomDatabaseCallback) in list of create object from database
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExcutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                /*
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);

                 */

                // this code will execute once when create database first time
                // write any code will take long time execution
//            EmployeeDao dao = INSTANCE.getEmployeeDao();
//            dao.deleteEmployee();
//
//            dao.insertEmployee(new Employee(1,"abdallah","faeq.abdallah"), new Employee(1,"abdallah","faeq.abdallah"));
            });


        }
    };

    /*
    // singleton and create database
    private static MyRoomDatabase instace;
    static MyRoomDatabase getInstace(Context context){
        if (instace == null){
            instace = Room.databaseBuilder(context, MyRoomDatabase.class,"room_db")
                    .build();
        }
        return instace;
    }*/


}
