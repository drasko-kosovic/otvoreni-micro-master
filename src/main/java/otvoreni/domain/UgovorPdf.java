package otvoreni.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UgovorPdf.
 */
@Entity
@Table(name = "view_ugovor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UgovorPdf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "broj_ugovora")
    private String broj_ugovora;

    @Column(name = "broj_tendera")
    private String broj_tendera;

    @Column(name = "broj_odluke")
    private String broj_odluke;

    @Column(name = "datum_ugovora")
    private LocalDate datum_ugovora;

    @Column(name = "datum_ponude")
    private LocalDate datum_ponude;

    @Column(name = "datum_odluke")
    private LocalDate datum_odluke;

    @Column(name = "predmet_ugovora")
    private String predmet_ugovora;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "naziv_ponudjaca")
    private String naziv_ponudjaca;

    @Column(name = "broj_datum_tenderske_dokumntacije")
    private String broj_datum_tenderske_dokumntacije;

    @Column(name = "broj_datum_odluke")
    private String broj_datum_odluke;

    @Column(name = "broj_datum_ponude")
    private String broj_datum_ponude;

    @Column(name = "iznos_ugovora_bez_pdf")
    private Double iznos_ugovora_bez_pdf;

    @Column(name = "sifra_postupka")
    private Integer sifra_postupka;

    @Column(name = "sifra_ponude")
    private Integer sifra_ponude;

    @Column(name = "odgovorno_lice")
    private String odgovorno_lice;

    @Column(name = "odgovorno_lice_narucioca")
    private String odgovorno_lice_narucioca;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "adresa_ponudjaca")
    private String adresa_ponudjaca;

    @Column(name = "banka_racun")
    private String banka_racun;

    @Column(name = "racun")
    private String racun;

    @Column(name = "pib")
    private String pib;

    @Column(name = "pdv")
    private String pdv;

    @Column(name = "datum_objave")
    private LocalDate datum_objave;

    @Column(name = "vrsta_postupka")
    private String vrsta_postupka;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UgovorPdf id(Long id) {
        this.id = id;
        return this;
    }

    public String getBroj_ugovora() {
        return this.broj_ugovora;
    }

    public UgovorPdf broj_ugovora(String broj_ugovora) {
        this.broj_ugovora = broj_ugovora;
        return this;
    }

    public void setBroj_ugovora(String broj_ugovora) {
        this.broj_ugovora = broj_ugovora;
    }

    public String getBroj_tendera() {
        return this.broj_tendera;
    }

    public UgovorPdf broj_tendera(String broj_tendera) {
        this.broj_tendera = broj_tendera;
        return this;
    }

    public void setBroj_tendera(String broj_tendera) {
        this.broj_tendera = broj_tendera;
    }

    public String getBroj_odluke() {
        return this.broj_odluke;
    }

    public UgovorPdf broj_odluke(String broj_odluke) {
        this.broj_odluke = broj_odluke;
        return this;
    }

    public void setBroj_odluke(String broj_odluke) {
        this.broj_odluke = broj_odluke;
    }

    public LocalDate getDatum_ugovora() {
        return this.datum_ugovora;
    }

    public UgovorPdf datum_ugovora(LocalDate datum_ugovora) {
        this.datum_ugovora = datum_ugovora;
        return this;
    }

    public void setDatum_ugovora(LocalDate datum_ugovora) {
        this.datum_ugovora = datum_ugovora;
    }

    public LocalDate getDatum_ponude() {
        return this.datum_ponude;
    }

    public UgovorPdf datum_ponude(LocalDate datum_ponude) {
        this.datum_ponude = datum_ponude;
        return this;
    }

    public void setDatum_ponude(LocalDate datum_ponude) {
        this.datum_ponude = datum_ponude;
    }

    public LocalDate getDatum_odluke() {
        return this.datum_odluke;
    }

    public UgovorPdf datum_odluke(LocalDate datum_odluke) {
        this.datum_odluke = datum_odluke;
        return this;
    }

    public void setDatum_odluke(LocalDate datum_odluke) {
        this.datum_odluke = datum_odluke;
    }

    public String getPredmet_ugovora() {
        return this.predmet_ugovora;
    }

    public UgovorPdf predmet_ugovora(String predmet_ugovora) {
        this.predmet_ugovora = predmet_ugovora;
        return this;
    }

    public void setPredmet_ugovora(String predmet_ugovora) {
        this.predmet_ugovora = predmet_ugovora;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public UgovorPdf naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public UgovorPdf telefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNaziv_ponudjaca() {
        return this.naziv_ponudjaca;
    }

    public UgovorPdf naziv_ponudjaca(String naziv_ponudjaca) {
        this.naziv_ponudjaca = naziv_ponudjaca;
        return this;
    }

    public void setNaziv_ponudjaca(String naziv_ponudjaca) {
        this.naziv_ponudjaca = naziv_ponudjaca;
    }

    public String getBroj_datum_tenderske_dokumntacije() {
        return this.broj_datum_tenderske_dokumntacije;
    }

    public UgovorPdf broj_datum_tenderske_dokumntacije(String broj_datum_tenderske_dokumntacije) {
        this.broj_datum_tenderske_dokumntacije = broj_datum_tenderske_dokumntacije;
        return this;
    }

    public void setBroj_datum_tenderske_dokumntacije(String broj_datum_tenderske_dokumntacije) {
        this.broj_datum_tenderske_dokumntacije = broj_datum_tenderske_dokumntacije;
    }

    public String getBroj_datum_odluke() {
        return this.broj_datum_odluke;
    }

    public UgovorPdf broj_datum_odluke(String broj_datum_odluke) {
        this.broj_datum_odluke = broj_datum_odluke;
        return this;
    }

    public void setBroj_datum_odluke(String broj_datum_odluke) {
        this.broj_datum_odluke = broj_datum_odluke;
    }

    public String getBroj_datum_ponude() {
        return this.broj_datum_ponude;
    }

    public UgovorPdf broj_datum_ponude(String broj_datum_ponude) {
        this.broj_datum_ponude = broj_datum_ponude;
        return this;
    }

    public void setBroj_datum_ponude(String broj_datum_ponude) {
        this.broj_datum_ponude = broj_datum_ponude;
    }

    public Double getIznos_ugovora_bez_pdf() {
        return this.iznos_ugovora_bez_pdf;
    }

    public UgovorPdf iznos_ugovora_bez_pdf(Double iznos_ugovora_bez_pdf) {
        this.iznos_ugovora_bez_pdf = iznos_ugovora_bez_pdf;
        return this;
    }

    public void setIznos_ugovora_bez_pdf(Double iznos_ugovora_bez_pdf) {
        this.iznos_ugovora_bez_pdf = iznos_ugovora_bez_pdf;
    }

    public Integer getSifra_postupka() {
        return this.sifra_postupka;
    }

    public UgovorPdf sifra_postupka(Integer sifra_postupka) {
        this.sifra_postupka = sifra_postupka;
        return this;
    }

    public void setSifra_postupka(Integer sifra_postupka) {
        this.sifra_postupka = sifra_postupka;
    }

    public Integer getSifra_ponude() {
        return this.sifra_ponude;
    }

    public UgovorPdf sifra_ponude(Integer sifra_ponude) {
        this.sifra_ponude = sifra_ponude;
        return this;
    }

    public void setSifra_ponude(Integer sifra_ponude) {
        this.sifra_ponude = sifra_ponude;
    }

    public String getOdgovorno_lice() {
        return this.odgovorno_lice;
    }

    public UgovorPdf odgovorno_lice(String odgovorno_lice) {
        this.odgovorno_lice = odgovorno_lice;
        return this;
    }

    public void setOdgovorno_lice(String odgovorno_lice) {
        this.odgovorno_lice = odgovorno_lice;
    }

    public String getOdgovorno_lice_narucioca() {
        return this.odgovorno_lice_narucioca;
    }

    public UgovorPdf odgovorno_lice_narucioca(String odgovorno_lice_narucioca) {
        this.odgovorno_lice_narucioca = odgovorno_lice_narucioca;
        return this;
    }

    public void setOdgovorno_lice_narucioca(String odgovorno_lice_narucioca) {
        this.odgovorno_lice_narucioca = odgovorno_lice_narucioca;
    }

    public String getAdresa() {
        return this.adresa;
    }

    public UgovorPdf adresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getAdresa_ponudjaca() {
        return this.adresa_ponudjaca;
    }

    public UgovorPdf adresa_ponudjaca(String adresa_ponudjaca) {
        this.adresa_ponudjaca = adresa_ponudjaca;
        return this;
    }

    public void setAdresa_ponudjaca(String adresa_ponudjaca) {
        this.adresa_ponudjaca = adresa_ponudjaca;
    }

    public String getBanka_racun() {
        return this.banka_racun;
    }

    public UgovorPdf banka_racun(String banka_racun) {
        this.banka_racun = banka_racun;
        return this;
    }

    public void setBanka_racun(String banka_racun) {
        this.banka_racun = banka_racun;
    }

    public String getRacun() {
        return this.racun;
    }

    public UgovorPdf racun(String racun) {
        this.racun = racun;
        return this;
    }

    public void setRacun(String racun) {
        this.racun = racun;
    }

    public String getPib() {
        return this.pib;
    }

    public UgovorPdf pib(String pib) {
        this.pib = pib;
        return this;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPdv() {
        return this.pdv;
    }

    public UgovorPdf pdv(String pdv) {
        this.pdv = pdv;
        return this;
    }

    public void setPdv(String pdv) {
        this.pdv = pdv;
    }

    public LocalDate getDatum_objave() {
        return this.datum_objave;
    }

    public UgovorPdf datum_objave(LocalDate datum_objave) {
        this.datum_objave = datum_objave;
        return this;
    }

    public void setDatum_objave(LocalDate datum_objave) {
        this.datum_objave = datum_objave;
    }

    public String getVrsta_postupka() {
        return this.vrsta_postupka;
    }

    public UgovorPdf vrsta_postupka(String vrsta_postupka) {
        this.vrsta_postupka = vrsta_postupka;
        return this;
    }

    public void setVrsta_postupka(String vrsta_postupka) {
        this.vrsta_postupka = vrsta_postupka;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UgovorPdf)) {
            return false;
        }
        return id != null && id.equals(((UgovorPdf) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UgovorPdf{" +
            "id=" + getId() +
            ", broj_ugovora='" + getBroj_ugovora() + "'" +
            ", broj_tendera='" + getBroj_tendera() + "'" +
            ", broj_odluke='" + getBroj_odluke() + "'" +
            ", datum_ugovora='" + getDatum_ugovora() + "'" +
            ", datum_ponude='" + getDatum_ponude() + "'" +
            ", datum_odluke='" + getDatum_odluke() + "'" +
            ", predmet_ugovora='" + getPredmet_ugovora() + "'" +
            ", naziv='" + getNaziv() + "'" +
            ", telefon='" + getTelefon() + "'" +
            ", naziv_ponudjaca='" + getNaziv_ponudjaca() + "'" +
            ", broj_datum_tenderske_dokumntacije='" + getBroj_datum_tenderske_dokumntacije() + "'" +
            ", broj_datum_odluke='" + getBroj_datum_odluke() + "'" +
            ", broj_datum_ponude='" + getBroj_datum_ponude() + "'" +
            ", iznos_ugovora_bez_pdf=" + getIznos_ugovora_bez_pdf() +
            ", sifra_postupka=" + getSifra_postupka() +
            ", sifra_ponude=" + getSifra_ponude() +
            ", odgovorno_lice='" + getOdgovorno_lice() + "'" +
            ", odgovorno_lice_narucioca='" + getOdgovorno_lice_narucioca() + "'" +
            ", adresa='" + getAdresa() + "'" +
            ", adresa_ponudjaca='" + getAdresa_ponudjaca() + "'" +
            ", banka_racun='" + getBanka_racun() + "'" +
            ", racun='" + getRacun() + "'" +
            ", pib='" + getPib() + "'" +
            ", pdv='" + getPdv() + "'" +
            ", datum_objave='" + getDatum_objave() + "'" +
            ", vrsta_postupka='" + getVrsta_postupka() + "'" +
            "}";
    }
}
