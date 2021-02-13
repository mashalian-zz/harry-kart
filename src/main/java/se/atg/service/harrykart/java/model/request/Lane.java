package se.atg.service.harrykart.java.model.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lane implements Serializable {

  @JacksonXmlProperty(isAttribute = true)
  private int number;

  @JacksonXmlText
  private int powerValue;

}


