package com.cg.oam.Entity;

import java.time.LocalDate;

import com.cg.oam.Bean.MedicineBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medicine {
	public Medicine(MedicineBean medicineBean) {
		medicineId=medicineBean.getMedicineId();
		medicineName=medicineBean.getMedicineName();
		medicineCost=medicineBean.getMedicineCost();
		companyName=medicineBean.getCompanyName();
		manufactureDate=medicineBean.getManufactureDate();
		expiryDate=medicineBean.getExpiryDate();
		stock=medicineBean.getStock();
		rating=medicineBean.getRating();
		
		if(medicineBean.getDescription()!=null)
			description=new Description(medicineBean.getDescription());
		
		/*
		 * if(medicineBean.getOrder()!=null) order=new
		 * OrderDetails(medicineBean.getOrder());
		 */
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medicine_id", nullable = false)
	private Integer medicineId; //primary key
	@Column(name = "medicine_name", nullable = false)
	private String medicineName;
	@Column(name = "medicine_cost")
	private float medicineCost;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "manufacture_date")
	private LocalDate manufactureDate;
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	@Column(name = "stock")
	private Integer stock;
	
	@Lob
	private Byte[] medicineImage;
	@Column(name = "rating")
	private Integer rating;
	
	//Bidirectional
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category medicineCategory;
	
	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="user_id") private Admin admin;
	 */
	
	//unidirectional
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "description_id")
	private Description description;
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name="order_id") private OrderDetails order;
	 */
}