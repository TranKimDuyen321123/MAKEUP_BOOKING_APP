import React, { useState, useEffect } from 'react';
import * as artistService from '../../services/artistService';
import Loading from '../common/Loading';
import ErrorMessage from '../common/ErrorMessage';

const ArtistList = () => {
  const [artists, setArtists] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadArtists = async () => {
      setLoading(true);
      setError(null);
      try {
        const data = await artistService.fetchArtists();
        setArtists(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    loadArtists();
  }, []);

  if (loading) return <Loading />;
  if (error) return <ErrorMessage message={error} />;

  return (
    <div>
      <h2>Danh sách Makeup Artist</h2>
      {artists.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Tên</th>
              <th>Email</th>
              <th>Số điện thoại</th>
            </tr>
          </thead>
          <tbody>
            {artists.map(artist => (
              <tr key={artist.id}>
                <td>{artist.id}</td>
                <td>{artist.name}</td>
                <td>{artist.email}</td>
                <td>{artist.phone}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Không có Makeup Artist nào.</p>
      )}
    </div>
  );
};

export default ArtistList;