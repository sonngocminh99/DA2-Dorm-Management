/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.RentBill;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se.alipsa.ymp.YearMonthPicker;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class ReportPaneController implements Initializable {
    private NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
    private RentBill rentBill;
    private ElectricAndWaterBill ewBill;
    private Student student;
    private Room room;
    @FXML
    private PieChart pieChart;
    @FXML
    private HBox box;
    private YearMonthPicker picker;
    @FXML
    private Label textDoanhThuThang;
    @FXML
    private Label textDoanhThuDaThu;
    @FXML
    private Label textDoanhThuChuaThu;
    @FXML
    private Label totalLabel;
    @FXML
    private Label totalRoomLabel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getTotalStudent();
        getTotalRoom();
        addYearMonthPicker();
        addDataToLabel();
        addDataToChart();
    }
    
    //hàm lấy dữ liệu thêm vào bảng thống kê
    private void addDataToChart() {
        //tạo các thuộc tính cho biểu đồ
        double totalPaid = getRoomChargePaid() + getEWChargePaid() + getRoomChargeUnpaid() + getEWChargeUnpaid();
        ObservableList<PieChart.Data> pieChartData = 
            FXCollections.observableArrayList(
                new PieChart.Data("Tiền phòng đã thu", getRoomChargePaid()/totalPaid * 100),
                new PieChart.Data("Tiền phòng chưa thu", getRoomChargeUnpaid()/totalPaid * 100),
                new PieChart.Data("Tiền điện nước đã thu", getEWChargePaid()/totalPaid * 100),
                new PieChart.Data("Tiền điện nước chưa thu", getEWChargeUnpaid()/totalPaid * 100)
                
            );
        //kiểm tra xem tháng này có dữ liệu hay không
        if(getRoomChargePaid() == 0 && getRoomChargeUnpaid() == 0 && getEWChargePaid() == 0 && getEWChargeUnpaid() == 0){
            pieChart.setData(pieChartData);
            pieChart.setTitle("Tháng này không có dữ liệu doanh thu");
        } else {
        pieChart.setData(pieChartData);
        pieChart.setTitle("Báo cáo doanh thu");
        //Hiển thị số phần trăm của thuộc tính
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue()));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
        }
    }
    //Hiển thị dữ liệu bằng label
    private void addDataToLabel() {
        double totalPaid = getRoomChargePaid() + getEWChargePaid();
        double totalUnpaid = getRoomChargeUnpaid() + getEWChargeUnpaid();
        double total = totalPaid + totalUnpaid;
        textDoanhThuThang.setText(nf.format(total));
        textDoanhThuDaThu.setText(nf.format(totalPaid));
        textDoanhThuChuaThu.setText(nf.format(totalUnpaid));
    }
    //tính tổng số tiền phòng
    private double getRoomCharge() {
        double total = 0.0;
        rentBill = new RentBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng các dữ liệu 
        for (var item: rentBill.getTotalRentBill(month, year,1)){
            total = total + item;
        };
        return total;
    }
    //tính tổng số tiền phòng đã trả
    private double getRoomChargePaid() {
        double total = 0.0;
        rentBill = new RentBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng cách lọc qua các dữ liệu 
        for (var item: rentBill.getTotalRentBill(month, year,2)){
            total = total + item;
        };
        return total;
    }
    //tính tổng số tiền phòng chưa trả
    private double getRoomChargeUnpaid() {
        double total = 0.0;
        rentBill = new RentBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng cách lọc qua các dữ liệu 
        for (var item: rentBill.getTotalRentBill(month, year,3)){
            total = total + item;
        };
        return total;
        
    }
    //tính tổng số tiền điên nước
    private double getEWCharge() {
        double total = 0.0;
        ewBill = new ElectricAndWaterBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng cách lọc qua các dữ liệu 
        for (var item: ewBill.getTotalEWBill(month, year,1)){
            
            total = total + item;
        };
        return total;
    }
    //tính tổng số tiền điện nước đã trả
    private double getEWChargePaid() {
        double total = 0.0;
        ewBill = new ElectricAndWaterBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng cách lọc qua các dữ liệu 
        for (var item: ewBill.getTotalEWBill(month, year,2)){
            
            total = total + item;
        };
        return total;
    }
    //tính tổng số tiền phòng chưa trả
    private double getEWChargeUnpaid() {
        double total = 0.0;
        ewBill = new ElectricAndWaterBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        //tính tổng số tiền bằng cách lọc qua các dữ liệu 
        for (var item: ewBill.getTotalEWBill(month, year,3)){
            
            total = total + item;
        };
        return total;
    }
    // tính tổng số sinh viên
    private void getTotalStudent() {
        student = new Student();       
        totalLabel.setText(Integer.toString(student.getTotalStudents()));
    }
    // tính tổng số phòng
    private void getTotalRoom() {
        room = new Room();
        totalRoomLabel.setText(Integer.toString(room.getTotalRooms()));
    }
    //thêm vào nút chọn ngày tháng
    private void addYearMonthPicker(){
        picker = new YearMonthPicker();
        picker.getStylesheets().add("/styles/yearmonthpicker.css");
        picker.setPrefSize(100, 45);
        picker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        picker.setId("yearMonthPicker");
        picker.setValue(YearMonth.now());
        picker.setOnAction((ActionEvent event) -> {
            addDataToChart();
            addDataToLabel();
        });
        box.getChildren().add(0, picker);
    }
    
}
