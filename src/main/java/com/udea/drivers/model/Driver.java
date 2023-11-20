package com.udea.drivers.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@ApiModel(description = "All details about driver")
public class Driver implements Serializable {

    @ApiModelProperty(notes = "The DB generated ID Driver")
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "First Name")
    @Column(name = "names", nullable = false, length = 80)
    private @NonNull String names;

    @ApiModelProperty(notes = "Last Name")
    @Column(name = "surnames", nullable = false, length = 80)
    private @NonNull String surnames;

    @ApiModelProperty(notes = "Number of services")
    @Column(name = "service_number", nullable = false, length = 80)
    private @NonNull int service_number;

    @ApiModelProperty(notes = "Email")
    @Column(name = "email", nullable = false, length = 80)
    private @NonNull String email;

    @ApiModelProperty(notes = "Age")
    @Column(name = "age", nullable = false, length = 80)
    private @NonNull String age;

    @ApiModelProperty(notes = "Cell Phone")
    @Column(name = "phone", nullable = false, length = 80)
    private @NonNull int phone;

    @ApiModelProperty(notes = "Document type")
    @Column(name = "document_type", nullable = false, length = 80)
    private @NonNull String document_type;

    @ApiModelProperty(notes = "Document")
    @Column(name = "document", nullable = false, length = 80)
    private @NonNull long document;

    @ApiModelProperty(notes = "Admission date")
    @Column(name = "admission_date", nullable = false, length = 80)
    private @NonNull LocalDateTime admissionDate;

    @ApiModelProperty(notes = "Driver State")
    @Column(name = "state", nullable = false, length = 80)
    private @NonNull StateDriverEnum state = StateDriverEnum.active;

    @ApiModelProperty(notes = "Driver Rating")
    @Column(name = "rating", nullable = false, length = 80)
    @Min(value = 1, message = "id should be more or than equal 1")
    @Max(value = 5, message = "id should be less or than equal 5")
    private @NonNull int rating;


//    @ApiModelProperty(notes = "Licencia de Conduccion")
//    @Column(name = "licenciaCon", nullable = false, length = 80)
//    private @NonNull String licenciaCon;
//
//    @ApiModelProperty(notes = "Available")
//    private Boolean available;

}

