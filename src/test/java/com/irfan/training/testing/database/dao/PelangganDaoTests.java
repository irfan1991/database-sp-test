package com.irfan.training.testing.database.dao;

import com.irfan.training.testing.database.entity.Pelanggan;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@SpringBootTest
@SqlGroup({
    // dinon-aktifkan, karena sudah ada clean data di method @After
    //@Sql(
    //        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, // ini dijalankan setelah method @After
    //        scripts = {"classpath:/delete-sample-data.sql"}
    //),
    @Sql(
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, // ini dijalankan sebelum method @Before
            scripts = {"classpath:/insert-sample-pelanggan.sql"}
    )
})
@RunWith(JUnitParamsRunner.class)
public class PelangganDaoTests {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private PelangganDao pelangganDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @After
    public void hapusData() throws Exception {
        System.out.println("Menghapus data pelanggan");
        String sql = "delete from pelanggan";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void testFindByEmail() {
        Pelanggan x = pelangganDao.findByEmail("irfan@pra.com");
        Assert.assertNotNull(x);
        System.out.println("Nama Pelanggan : " + x.getNama());
        Assert.assertEquals("Irfan", x.getNama());

        Pelanggan y = pelangganDao.findByEmail("irfan@pra.com");
        Assert.assertNull(y);
    }
    
    @Test
    @FileParameters(value = "classpath:pelanggan.csv", mapper = CsvWithHeaderMapper.class)
    public void testFindByEmailParameterized(String email, String nama, Boolean ada){
        System.out.println("Mencari data dengan email : "+email+" dan nama "+nama);
        Pelanggan x = pelangganDao.findByEmail(email);
        Assert.assertEquals(ada, (x != null));
        if(x!=null){
            Assert.assertEquals(nama, x.getNama());
            System.out.println("Nama : "+x.getNama());
        }
    }
}
