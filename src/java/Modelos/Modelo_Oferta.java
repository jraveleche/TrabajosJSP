/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Classes.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Classes.Oferta;
import Classes.Puesto;
import Classes.Usuario;
import java.sql.SQLException;

/**
 *
 * @author tosh
 */
public class Modelo_Oferta {

    Modelo_ConexionDb db = new Modelo_ConexionDb();
    String sql = "";
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    String sql1 = "";
    Connection conn1;
    PreparedStatement pst1;
    ResultSet rs1;

    public List obtenerOfertas() {
        List ofertas = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            //sql = "SELECT * FROM oferta WHERE oferta.estado = 1";
            sql = "SELECT * FROM oferta WHERE oferta.estado = 1 ";
            /*sql = "SELECT  *" +
                    "FROM oferta,\n" +
                    "	(\n" +
                    "	SELECT ofertaUsuario.oferta_oferta, ofertaUsuario.fecha\n" +
                    "    FROM ofertaUsuario \n" +
                    "    ) AS O\n" +
                    "WHERE oferta.oferta = O.oferta_oferta\n" +
                    "ORDER BY O.fecha ASC";
             */
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
        }
        return ofertas;
    }

    public List obtenerCategorias() {
        List categorias = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT  * FROM categoria;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Categoria nuevaCategoria = new Categoria(rs.getString("categoria"), rs.getString("nombre"), rs.getString("descripcion"));
                categorias.add(nuevaCategoria);
            }

            conn.close();
            rs.close();
            return categorias;
        } catch (Exception e) {
        }
        return categorias;
    }

    public List obtenerPuestos() {
        List puestos = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT  * FROM puesto;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Puesto nuevoPuesto = new Puesto(rs.getString("puesto"), rs.getString("nombre"), rs.getString("descripcion"));
                puestos.add(nuevoPuesto);
            }

            conn.close();
            rs.close();
            return puestos;
        } catch (Exception e) {
        }
        return puestos;
    }

    public List obtenerOfertasCategorias(String id) {
        List ofertas = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM oferta WHERE oferta.categoria_categoria = '" + id + "';";
            /*sql = "SELECT *\n" +
                    "FROM oferta, \n" +
                    "	(\n" +
                    "    SELECT oferta_oferta \n" +
                    "    FROM ofertaUsuario, \n" +
                    "    	(\n" +
                    "         SELECT ofertaCategoria.ofertaUsuario_usuario_usuario, ofertaCategoria.ofertaUsuario_oferta_oferta \n" +
                    "         FROM ofertaCategoria \n" +
                    "         WHERE ofertaCategoria.categoria_categoria = "+id+" \n" +
                    "        )AS C \n" +
                    "     WHERE ofertaUsuario.usuario_usuario = C.ofertaUsuario_usuario_usuario \n" +
                    "     AND ofertaUsuario.oferta_oferta = C.ofertaUsuario_oferta_oferta \n" +
                    "    )AS O \n" +
                    "WHERE oferta.oferta = O.oferta_oferta;";
             */
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ofertas;
    }

    public List obtenerOfertasPuesto(String id) {
        List ofertas = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM oferta WHERE oferta.puesto_puesto = '" + id + "';";
            /*sql = "SELECT  *\n" +
                    "FROM 	oferta,\n" +
                    "		(\n" +
                    "        SELECT ofertaUsuario.oferta_oferta\n" +
                    "        FROM 	ofertaUsuario, \n" +
                    "                (SELECT puesto FROM puesto WHERE puesto = "+id+") AS P\n" +
                    "        WHERE 	ofertaUsuario.puesto_puesto	=	P.puesto\n" +
                    "        )AS O\n" +
                    "WHERE oferta.oferta	=	O.oferta_oferta";
             */
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ofertas;
    }

    public List obtenerOfertasPorUsuario(String correo) {

        List ofertas = new ArrayList();
        String usuario;
        int rquery = 0;

        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());

            sql = "SELECT *\n"
                    + "FROM oferta, \n"
                    + "(SELECT oferta_oferta\n"
                    + "FROM ofertaUsuario,\n"
                    + "(\n"
                    + "SELECT usuario\n"
                    + "FROM usuario\n"
                    + "WHERE usuario.correo = '" + correo + "'\n"
                    + ") AS c\n"
                    + "WHERE usuario_usuario = c.usuario\n"
                    + ") AS U\n"
                    + "WHERE oferta.oferta = U.oferta_oferta "
                    + "AND oferta.estado = 1;";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
        }
        return ofertas;
    }

    /*
        tosh    -   Utiliza relacion de muchos-muchos, hay que ir a traer varias llaves ....
     */
    public int crearOferta(String correo, String titulo, String descripcion,
            String numeroPlazas, String nivelExperiencia, String salario,
            String vehiculo, String categoria, String puesto) {
        int rquery = 0;
        /*  determina si el query fue ejecutado correctamente*/
        String usuario;
        String oferta;
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB

            /*  Creacion De Oferta (Tabla 'oferta')*/
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "INSERT INTO oferta (oferta, titulo, descripcion, numeroPlazas, nivelExperiencia, salario, vehiculo,fecha, estado, categoria_categoria, puesto_puesto)"
                    + " VALUES (NULL,'" + titulo + "','" + descripcion + "','" + numeroPlazas + "','" + nivelExperiencia + "','" + salario
                    + "','" + vehiculo + "',NOW(),'1','" + categoria + "','" + puesto + "');";

            pst = conn.prepareStatement(sql, new String[]{"oferta"});
            rquery = pst.executeUpdate();
            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                oferta = rs.getString(1);
            } else {
                return rquery;
            }

            /*  Obtenemos id(usuario) de la persona que hizo la oferta*/
            PreparedStatement pstUsuario;
            String sql_usuario = "SELECT usuario FROM usuario WHERE correo='" + correo + "';";

            pstUsuario = conn.prepareStatement(sql_usuario);
            ResultSet rsUsuario = pstUsuario.executeQuery();

            if (rsUsuario.next()) {
                usuario = rsUsuario.getString("usuario");
            } else {
                return rquery;
            }

            /*  Insercion de llaves en: ofertausuario*/
            PreparedStatement pstOfertaUsuario;
            String sql_ofertaUsuario = "INSERT INTO ofertaUsuario (usuario_usuario, oferta_oferta)"
                    + "VALUES ('" + usuario + "','" + oferta + "');";

            pstOfertaUsuario = conn.prepareStatement(sql_ofertaUsuario);
            rquery = pstOfertaUsuario.executeUpdate();

            rsUsuario.close();

            rs.close();

            conn.close();

            return rquery;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Grupo 8-Error: " + e);
        }
        return 0;
    }

    public List obtenerOfertasMasRecientes() {
        List ofertas = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM oferta ORDER BY oferta.fecha DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
        }
        return ofertas;
    }

    public List obtenerOfertasMenosRecientes() {
        List ofertas = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM oferta ORDER BY oferta.fecha ASC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                ofertas.add(nuevaOferta);
            }

            conn.close();
            rs.close();
            return ofertas;
        } catch (Exception e) {
        }
        return ofertas;
    }

    public Oferta obtenerOferta(String idOferta) {
        Oferta oferta = null;
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            conn1 = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM oferta WHERE oferta.oferta = '" + idOferta + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                oferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                oferta.setEstado(rs.getString("estado"));
                //seleccion del nombre de categoria por medio del id
                sql1 = "SELECT * FROM categoria WHERE categoria.categoria = '" + rs.getString("categoria_categoria") + "'";
                pst1 = conn1.prepareStatement(sql1);
                rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    oferta.setCategoria(rs1.getString("nombre"));
                }
                rs1.close();

                sql1 = "SELECT * FROM puesto WHERE puesto.puesto = '" + rs.getString("puesto_puesto") + "'";
                pst1 = conn1.prepareStatement(sql1);
                rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    oferta.setPuesto(rs1.getString("nombre"));
                }
                rs1.close();
            }

            conn.close();
            rs.close();
            return oferta;
        } catch (Exception e) {

        }
        return oferta;
    }

    public List miOferta(String usuario) {
        List oferta = new ArrayList();
        try {

            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT o.oferta, o.titulo, o.descripcion, o.numeroPlazas, o.salario, o.nivelExperiencia, o.salario, o.vehiculo\n"
                    + ", o.categoria_categoria, o.puesto_puesto, o.estado \n"
                    + "FROM oferta o, usuario u, ofertaUsuario oU\n"
                    + "WHERE u.usuario =  oU.usuario_usuario\n"
                    + "AND o.oferta = oU.oferta_oferta\n"
                    + "AND u.correo = '" + usuario + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Oferta nuevaOferta = new Oferta(rs.getString("oferta"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("numeroPlazas"), rs.getString("nivelExperiencia"),
                        rs.getString("salario"), rs.getString("vehiculo"));
                nuevaOferta.setEstado(rs.getString("estado"));
                nuevaOferta.setCategoria(rs.getString("categoria_categoria"));
                nuevaOferta.setPuesto(rs.getString("puesto_puesto"));
                /*Oferta nuevaOferta = new Oferta(rs.getString("oferta"),rs.getString("titulo"),rs.getString("descripcion"),
                                                rs.getString("numeroPlazas"),rs.getString("nivelExperiencia"),
                                                rs.getString("salario"),rs.getString("vehiculo"));*/
                oferta.add(nuevaOferta);

            }

            conn.close();
            rs.close();
            return oferta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return oferta;
    }

    public int eliminarOferta(String idOferta) {
        /*  tosh - Oferta no se elimina, solo se actualiza su campo estado*/
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());

            sql = "UPDATE oferta SET oferta.estado = 0 WHERE oferta.oferta = '" + idOferta + "'";
            pst = conn.prepareStatement(sql);

            pst.executeUpdate();
            conn.close();

            return 1;
        } catch (Exception e) {
        }
        return 0;
    }

    public int publicarOferta(String idOferta) {
        /*  tosh - Oferta no se elimina, solo se actualiza su campo estado*/
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());

            sql = "UPDATE oferta SET oferta.estado = 1 WHERE oferta.oferta = '" + idOferta + "'";
            pst = conn.prepareStatement(sql);

            pst.executeUpdate();
            conn.close();

            return 1;
        } catch (Exception e) {
        }
        return 0;
    }

    public int actualizarOferta(String idOferta, String titulo, String descripcion,
            String numeroPlazas, String nivelExperiencia, String salario,
            String vehiculo, String categoria, String puesto) {
        int rquery = 0;
        /*  determina si el query fue ejecutado correctamente*/

        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB

            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "UPDATE oferta SET titulo='" + titulo + "',descripcion='" + descripcion + "',numeroPlazas='" + numeroPlazas
                    + "',nivelExperiencia='" + nivelExperiencia + "',salario='" + salario
                    + "',vehiculo='" + vehiculo + "',categoria_categoria='" + categoria + "',puesto_puesto='" + puesto + "' "
                    + "WHERE oferta.oferta = '" + idOferta + "';";

            pst = conn.prepareStatement(sql);
            rquery = pst.executeUpdate();

            conn.close();

            return rquery;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Grupo 8-Error: " + e);
        }
        return 0;
    }

    public List postulantes(String idOferta) {
        List postulantes = new ArrayList();
        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT usuario.nombre, usuario.apellido, usuario.correo, usuario.edad, usuario.sexo, usuario.usuario\n"
                    + "FROM usuario,\n"
                    + "	(\n"
                    + "    SELECT *\n"
                    + "    FROM postulacion\n"
                    + "    WHERE postulacion.ofertaUsuario_oferta_oferta	= '" + idOferta + "'\n"
                    + "    ) AS P\n"
                    + "WHERE usuario.usuario = P.usuario_usuario";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo"),
                        rs.getString("edad"), rs.getString("sexo"));
                usuario.setUsuario(rs.getString("usuario"));
                postulantes.add(usuario);
            }

            conn.close();
            rs.close();
            return postulantes;
        } catch (Exception e) {
        }
        return postulantes;
    }

    public int postularse(String correo, String idOferta) {
        int rquery = 0;
        /*  determina si el query fue ejecutado correctamente*/

        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB

            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            /*Esta query obtiene el id de la persona que hizo la oferta y ala que se va a postular*/
            sql = "INSERT INTO postulacion(fecha, estado, ofertaUsuario_usuario_usuario, ofertaUsuario_oferta_oferta, usuario_usuario) "
                    + "VALUES (NOW(),'0',"
                    + "(SELECT ofertausuario.usuario_usuario FROM ofertausuario WHERE  ofertausuario.oferta_oferta = '" + idOferta + "'),"
                    + "'" + idOferta + "',"
                    + "(SELECT usuario.usuario FROM usuario WHERE usuario.correo = '" + correo + "'));";

            pst = conn.prepareStatement(sql);
            rquery = pst.executeUpdate();

            conn.close();

            return rquery;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Grupo 8-Error: " + e);
        }
        return rquery;
    }

    public int isOfertaUsuario(String codigo, String usuario) {

        try {
            Class.forName(db.getDriver());  //Crea Conexion con DB
            conn = DriverManager.getConnection(db.getUrl(), db.getUserdb(), db.getPassdb());
            sql = "SELECT * FROM ofertaUsuario WHERE oferta_oferta='" + codigo + "'  && usuario_usuario=(select usuario from usuario where correo='" + usuario + "');";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {
                conn.close();
                rs.close();
                return 1;
            }
            conn.close();
            rs.close();
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            return 3;
        }
    }

}
