0x00408071:	pusha
0x00408072:	call 0x00408077
0x00408077:	popl %eax
0x00408078:	addl %eax, $0x29f<UINT32>
0x0040807d:	movl %esi, (%eax)
0x0040807f:	addl %esi, %eax
0x00408081:	subl %eax, %eax
0x00408083:	movl %edi, %esi
0x00408085:	lodsw %ax, %ds:(%esi)
0x00408087:	shll %eax, $0xc<UINT8>
0x0040808a:	movl %ecx, %eax
0x0040808c:	pushl %eax
0x0040808d:	lodsl %eax, %ds:(%esi)
0x0040808e:	subl %ecx, %eax
0x00408090:	addl %esi, %ecx
0x00408092:	movl %ecx, %eax
0x00408094:	pushl %edi
0x00408095:	pushl %ecx
0x00408096:	decl %ecx
0x00408097:	movb %al, 0x6(%ecx,%edi)
0x0040809b:	movb (%ecx,%esi), %al
0x0040809e:	jne 0x00408096
0x004080a0:	movl %edx, %esi
0x004080a2:	movl %ecx, %edi
0x004080a4:	call 0x00408105
0x00408105:	pushl %ebp
0x00408106:	movl %ebp, %esp
0x00408108:	subl %esp, $0x14<UINT8>
0x0040810b:	movb %al, (%edx)
0x0040810d:	pushl %esi
0x0040810e:	xorl %esi, %esi
0x00408110:	incl %esi
0x00408111:	cmpl 0x8(%ebp), %esi
0x00408114:	movl -16(%ebp), %ecx
0x00408117:	movb (%ecx), %al
0x00408119:	movl -8(%ebp), %esi
0x0040811c:	movb -1(%ebp), $0x0<UINT8>
0x00408120:	jbe 483
0x00408126:	pushl %ebx
0x00408127:	pushl %edi
0x00408128:	cmpb -1(%ebp), $0x0<UINT8>
0x0040812c:	movb %cl, (%edx,%esi)
0x0040812f:	je 0x0040813d
0x0040813d:	incl %esi
0x0040813e:	andl -12(%ebp), $0x0<UINT8>
0x00408142:	movb -2(%ebp), %cl
0x00408145:	movzbl %eax, -1(%ebp)
0x00408149:	movl %edi, 0x8(%ebp)
0x0040814c:	subl %edi, %eax
0x0040814e:	cmpl %esi, %edi
0x00408150:	jae 416
0x00408156:	testb %cl, %cl
0x00408158:	jns 0x00408275
0x0040815e:	cmpb -1(%ebp), $0x0<UINT8>
0x00408275:	cmpb -1(%ebp), $0x0<UINT8>
0x00408279:	movzbl %ebx, (%edx,%esi)
0x0040827d:	je 0x0040828c
0x0040828c:	movl %edi, -8(%ebp)
0x0040828f:	movl %eax, -16(%ebp)
0x00408292:	incl -8(%ebp)
0x00408295:	movb (%eax,%edi), %bl
0x00408298:	incl %esi
0x00408299:	incl -12(%ebp)
0x0040829c:	shlb %cl
0x0040829e:	cmpl -12(%ebp), $0x8<UINT8>
0x004082a2:	movb -2(%ebp), %cl
0x004082a5:	jl 0x00408145
0x004082ab:	jmp 0x004082f6
0x004082f6:	movzbl %eax, -1(%ebp)
0x004082fa:	movl %ecx, 0x8(%ebp)
0x004082fd:	subl %ecx, %eax
0x004082ff:	cmpl %esi, %ecx
0x00408301:	jb 0x00408128
0x00408162:	movl %ebx, (%edx,%esi)
0x00408165:	je 0x0040816a
0x0040816a:	andl %ebx, $0xfffff<UINT32>
0x00408170:	incl %esi
0x00408171:	cmpl -8(%ebp), $0x881<UINT32>
0x00408178:	movl %edi, %ebx
0x0040817a:	jae 32
0x0040817c:	shrl %edi
0x0040817e:	testb %bl, $0x1<UINT8>
0x00408181:	je 0x00408197
0x00408197:	andl %edi, $0x7f<UINT8>
0x0040819a:	jmp 0x004081e1
0x004081e1:	incl %edi
0x004081e2:	cmpb -1(%ebp), $0x0<UINT8>
0x004081e6:	je 0x004081f1
0x004081f1:	xorl %ebx, %ebx
0x004081f3:	movw %bx, (%edx,%esi)
0x004081f7:	andl %ebx, $0xfff<UINT32>
0x004081fd:	movzbl %eax, -1(%ebp)
0x00408201:	xorb -1(%ebp), $0x1<UINT8>
0x00408205:	addl %esi, %eax
0x00408207:	movl %eax, %ebx
0x00408209:	andl %eax, $0xf<UINT8>
0x0040820c:	cmpl %eax, $0xf<UINT8>
0x0040820f:	je 5
0x00408211:	leal %ebx, 0x3(%eax)
0x00408214:	jmp 0x0040824e
0x0040824e:	movl %eax, -8(%ebp)
0x00408251:	subl %eax, %edi
0x00408253:	testl %ebx, %ebx
0x00408255:	je 66
0x00408257:	movl %edi, -16(%ebp)
0x0040825a:	addl %eax, %edi
0x0040825c:	movl -20(%ebp), %ebx
0x0040825f:	movl %ebx, -8(%ebp)
0x00408262:	movb %cl, (%eax)
0x00408264:	incl -8(%ebp)
0x00408267:	incl %eax
0x00408268:	decl -20(%ebp)
0x0040826b:	movb (%edi,%ebx), %cl
0x0040826e:	jne 0x0040825f
0x00408270:	movb %cl, -2(%ebp)
0x00408273:	jmp 0x00408299
0x00408131:	movb %al, 0x1(%edx,%esi)
0x00408135:	shrb %cl, $0x4<UINT8>
0x00408138:	shlb %al, $0x4<UINT8>
0x0040813b:	orb %cl, %al
0x00408183:	andl %edi, $0x7ff<UINT32>
0x00408189:	addl %esi, %eax
0x0040818b:	addl %edi, $0x81<UINT32>
0x00408191:	xorb -1(%ebp), $0x1<UINT8>
0x00408195:	jmp 0x004081e2
0x004081e8:	movzwl %ebx, (%edx,%esi)
0x004081ec:	shrl %ebx, $0x4<UINT8>
0x004081ef:	jmp 0x004081fd
