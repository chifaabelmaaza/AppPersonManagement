package ma.emsi.applicationgestionpersonne.dao.impl;

import java.sql.Connection;
import java.util.Date; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.emsi.applicationgestionpersonne.dao.PersonDao;
import ma.emsi.applicationgestionpersonne.entities.Person;

public class PersonDaoImp implements PersonDao {

	private Connection conn= DB.getConnection();

	@Override
	public void insert(Person person) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO person (name,age,gender,address,email,phoneNumber,dateNaiss,cin) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, person.getName());
			ps.setInt(2, person.getAge());
			ps.setString(3, person.getGender());
			ps.setString(4, person.getAddress());
			ps.setString(5, person.getEmail());
			ps.setString(6, person.getPhoneNumber());
			ps.setDate(7, new java.sql.Date( person.getDateNaiss().getTime()));
			System.out.println(new java.sql.Date( person.getDateNaiss().getTime()));
			ps.setString(8, person.getCin());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					person.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un département");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Person person) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE person SET name= ?,age= ?,gender = ?,address = ?,email = ?,phoneNumber = ?,dateNaiss = ? ,cin = ? WHERE id = ?");

			ps.setString(1, person.getName());
			ps.setInt(2, person.getAge());
			ps.setString(3, person.getGender());
			ps.setString(4, person.getAddress());
			ps.setString(5, person.getEmail());
			ps.setString(6, person.getPhoneNumber());
			ps.setDate(7, new java.sql.Date( person.getDateNaiss().getTime()));
			ps.setString(8, person.getCin());
			ps.setInt(9, person.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'une personne");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM person WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'une personne");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public Person findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM person WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setGender(rs.getString("gender"));
				person.setAddress(rs.getString("address"));
				person.setEmail(rs.getString("email"));
				person.setPhoneNumber(rs.getString("phoneNumber"));
				person.setDateNaiss(rs.getDate("dateNaiss"));
				person.setCin(rs.getString("cin"));

				return person;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver une personne");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	@Override
	public Person findByIdentity(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM person WHERE name = ?");

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setGender(rs.getString("gender"));
				person.setAddress(rs.getString("address"));
				person.setEmail(rs.getString("email"));
				person.setPhoneNumber(rs.getString("phoneNumber"));
				person.setDateNaiss(rs.getDate("dateNaiss"));
				person.setCin(rs.getString("cin"));

				return person;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver une personne");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
	
	@Override
	public Person findByIdentity(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM person WHERE cin = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setGender(rs.getString("gender"));
				person.setAddress(rs.getString("address"));
				person.setEmail(rs.getString("email"));
				person.setPhoneNumber(rs.getString("phoneNumber"));
				person.setDateNaiss(rs.getDate("dateNaiss"));
				person.setCin(rs.getString("cin"));

				return person;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver une personne");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
	
	@Override
	public List<Person> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM person");
			rs = ps.executeQuery();

			List<Person> listDepartment = new ArrayList<>();

			while (rs.next()) {
				Person person = new Person();

				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setGender(rs.getString("gender"));
				person.setAddress(rs.getString("address"));
				person.setEmail(rs.getString("email"));
				person.setPhoneNumber(rs.getString("phoneNumber"));
				person.setDateNaiss(rs.getDate("dateNaiss"));
				person.setCin(rs.getString("cin"));

				listDepartment.add(person);
			}

			return listDepartment;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner une personne");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

}
