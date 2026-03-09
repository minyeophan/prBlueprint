interface Props {
  name: string;
}

function HelloBlueprint({ name }: Props) {
  return (
    <div style={{ textAlign: 'center', marginTop: '100px' }}>
      <h1>Hello {name}!</h1>
      <p>Phase 7 프론트엔드 연결 성공</p>
    </div>
  );
}

export default HelloBlueprint;
